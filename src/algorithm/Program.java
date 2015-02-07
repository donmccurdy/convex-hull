package algorithm;

import gui.CanvasPanel;
import gui.ControlPanel;
import gui.Subpanel_Run;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import javax.swing.SwingWorker;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Program implements ActionListener{
	public ArrayList<Point> points;
	public HashSet<Hull> hulls;
	public Tangent tangent = null;
	//public ArrayList<Edge> edges;

	protected boolean running = false;
	protected RunTask task;
	public Subpanel_Run runPanel;
	public ControlPanel controlPanel;

	public Stack<ChangeListener> listeners;


	public Program () {
		points = new ArrayList<Point>();
		//edges = new ArrayList<Edge>();
		hulls = new HashSet<Hull>();
		listeners = new Stack<ChangeListener>();
	}

	public ArrayList<Point> getPoints() { return points; }
	public Set<Hull> getHulls() { return hulls; }
	public void addPoint(Point p) { 
		if (running) return;
		points.add(p); 
		//if (points.size() ==3 ) System.out.println(Point.isLeftTurn(points.get(0), points.get(1), points.get(2)));
	}
	protected void clear() {
		points.clear();
		hulls.clear();
		tangent = null;
		notifyListeners();
	}

	////////////////////////////////////////////////////////////////////////
	//																							THE ALGORITHM
	////////////////////////////////////////////////////////////////////////

	private class RunTask extends SwingWorker<Void, TangentAndHulls > implements TangentReceiving {
		List<Point> threadPoints;
		HashSet<Hull> threadHulls;
		Tangent threadTangent = null;

		public boolean paused = false;
		protected boolean didFinalPublish = false;

		public RunTask(List<Point> points) {
			super();
			this.threadPoints = points;
			threadHulls = new HashSet<Hull>();
		}

		@Override
		protected Void doInBackground() throws Exception {
			setRunning(true);
			customRun();
			if (!didFinalPublish)
				setRunning(false);

			return null;
		}

		public void customRun() {
			if (threadPoints.size() == 0) return;
			Collections.sort(threadPoints);
			customNotify();
			customWait(1000);
			recurse(threadPoints);
		}

		protected Hull recurse (List<Point> P) {
			if (P.size() <= 3) {
				Hull h = new Hull(P);
				threadHulls.add(h);
				
				customNotify();
				customWait(500);
				
				return h;
			}

			Hull left = recurse(P.subList(0, P.size()/2));
			Hull right = recurse(P.subList(P.size()/2, P.size()));
			Hull union = Hull.merge(this, left, right);

			threadHulls.remove(left);
			threadHulls.remove(right);
			threadHulls.add(union);

			customNotify();
			customWait(1000);

			return union;	
		}

		protected void customNotify() {
			if (didFinalPublish) return;
			
			while (paused)
				customWait(500);
			if (isCancelled()) {
				setRunning(false);
				threadHulls.clear();
				didFinalPublish = true;
			}
				
			publish(new TangentAndHulls(threadHulls, threadTangent));
		}

		public void customWait(int i) {
			try {
				Thread.sleep(i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		@Override
		protected void process (List< TangentAndHulls > allHulls) {
			TangentAndHulls newHulls = allHulls.get(allHulls.size() -1);
			hulls = (HashSet<Hull>) newHulls.hulls;
			tangent = newHulls.tangent;
			notifyListeners();
		}

		@Override
		public void setTangent(Tangent t, Hull left, Hull right) {
			if (t == null) {
				threadTangent = null;
				customNotify();
				return;
			}
			
			threadTangent = t;
			threadTangent.a = left.get(threadTangent.left);
			threadTangent.b = right.get(threadTangent.right);
			customNotify();
			customWait(500);
		}

	}


	////////////////////////////////////////////////////////////////////////
	//																					    LISTENER METHODS
	////////////////////////////////////////////////////////////////////////

	protected void notifyListeners() { 
		ChangeEvent event = new ChangeEvent(this);
		for (ChangeListener l : listeners) 
			if (l instanceof CanvasPanel) l.stateChanged(event);
	}

	public void setRunning(boolean running) {
		controlPanel.setEnabled(!running);
		runPanel.setRunEnabled(!running);
		runPanel.setPauseEnabled(running);
		runPanel.setResetEnabled(running);
		runPanel.togglePausePlay(true);
		tangent = null;
		this.running = running;
	}

	public void addChangeListener(ChangeListener l) {
		listeners.push(l);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if (cmd.equals("rando")) {														//RANDOMIZE
			clear();
			int num =(int) (Math.random()*25 + 5);
			for (int i = 0; i < num; ++i) {
				this.addPoint(new Point((int) (Math.random()*610.0) + 20 
						, (int) ( Math.random()*260.0) + 20));
			}
		} else if (cmd.equals("clear")) {												//CLEAR
			clear();
		} else if (cmd.equals("run")) {													//RUN
			//run();
			(task = new RunTask(points)).execute();
		} else if (cmd.equals("reset")) {												//RESET
			task.cancel(false);
			//task.cancel(arg0)
		} else if (cmd.equals("pause")) {
			task.paused = !task.paused;
			runPanel.togglePausePlay(!task.paused);
		}
		else {																						//ERROR CASE
			System.out.println("Action \"" + cmd + "\" Not Recognized.");
		}

		notifyListeners();
	}

}

class TangentAndHulls {
	public Set<Hull> hulls;
	public Tangent tangent;
	
	public TangentAndHulls(Set<Hull> hulls, Tangent tan) {
		this.hulls = hulls;
		this.tangent = tan;
	}
}

interface TangentReceiving {
	public void setTangent(Tangent t,Hull left, Hull right);
}
