import java.awt.EventQueue;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.border.BevelBorder;

import edu.uwm.cs351.BSTInteger;
import edu.uwm.cs351.BSTInteger.BSTNode;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class BSTApplet {

	private JFrame frmBstIteratorDemo;
	private BSTCanvas canvas;
	private final BSTInteger bst1 = new BSTInteger(1);
	private final BSTInteger bst2 = new BSTInteger(2);
	private final BSTInteger bst3 = new BSTInteger(3);
	private final BSTInteger bst4 = new BSTInteger(4);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
			        UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
					BSTApplet window = new BSTApplet();
					window.frmBstIteratorDemo.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BSTApplet() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmBstIteratorDemo = new JFrame();
		frmBstIteratorDemo.setTitle("BST Iterator Demo");
		frmBstIteratorDemo.setResizable(false);
		frmBstIteratorDemo.setBounds(100, 100, 405, 295);
		frmBstIteratorDemo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmBstIteratorDemo.getContentPane().setLayout(null);
		
		JButton btnNext = new JButton("Advance");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					canvas.next();
				}
				catch (IllegalStateException e) {
						JOptionPane.showMessageDialog(frmBstIteratorDemo,  "There is no current element", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNext.setBounds(141, 229, 117, 25);
		frmBstIteratorDemo.getContentPane().add(btnNext);
		
		JButton btnReset = new JButton("Start");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.reset();
			}
		});
		btnReset.setBounds(13, 229, 117, 25);
		frmBstIteratorDemo.getContentPane().add(btnReset);
		
		JButton btnChange = new JButton("Change Tree");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				canvas.changeTree();
			}
		});
		btnChange.setBounds(269, 229, 117, 25);
		frmBstIteratorDemo.getContentPane().add(btnChange);		
		
		canvas = new BSTCanvas(bst1);
		canvas.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		canvas.setBackground(Color.WHITE);
		canvas.setBounds(12, 10, 374, 210);
		frmBstIteratorDemo.getContentPane().add(canvas);
	}
	
	private class BSTCanvas extends JPanel {
		private static final int BOX_WIDTH = 40, BOX_HEIGHT = 20, Y_SPACING = 10;
		private BSTInteger bst;
		private int current;
		
		public BSTCanvas(BSTInteger b) {
			super();
			bst = b;
			current=0;
		}
		
		public void reset(){
			bst.start();
			try {
				current = bst.getCurrent();
			}
			catch(IllegalStateException e) {
				current = 0;
			}
			repaint();
		}
		
		public void next(){
			NullPointerException npe = null;
			try {
				bst.advance();
			}
			catch(IllegalStateException e) {
				throw new IllegalStateException();
			}
			catch(NullPointerException e) {
				npe = e;
			}
			try {
				current = bst.getCurrent();
			}
			catch(IllegalStateException e) {
				current = 0;
			}
			repaint();
			if (npe != null) throw npe;
		}
		
		public void changeTree(){
			if(bst == bst1)
				bst = bst2;
			else if(bst == bst2)
				bst = bst3;
			else if(bst == bst3)
				bst = bst4;
			else bst = bst1;
			bst.reset();
			current = 0;
			repaint();
		}
		
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			paintTree(g, this.getWidth(), 0, 10, bst.root);
		}
		
		private boolean paintTree(Graphics g, int width, int leftEdge, int y, BSTNode n) {
			if (n == null) return false;
			int midPoint = leftEdge + width / 2;
			int x = midPoint - BOX_WIDTH / 2;
			if (current == n.data) {
				paintBox(g, x, y, Integer.toString(n.data), Color.cyan);
			}
			else {
				paintBox(g, x, y, Integer.toString(n.data));
			}
			if (paintTree(g,width/2, leftEdge, y + BOX_HEIGHT + Y_SPACING, n.left))
				g.drawLine(midPoint, y + BOX_HEIGHT, width/4 + leftEdge, y + BOX_HEIGHT + Y_SPACING);
			if (paintTree(g,width/2, midPoint, y + BOX_HEIGHT + Y_SPACING, n.right))
				g.drawLine(midPoint, y + BOX_HEIGHT, width/4 * 3 + leftEdge, y + BOX_HEIGHT + Y_SPACING);
			return true;
			
		}
		
		private void paintBox(Graphics g, int x, int y, String text) {
			g.drawRect(x, y, BOX_WIDTH, BOX_HEIGHT);
			FontMetrics fm = getFontMetrics( getFont() );
			int textWidth = fm.stringWidth(text);
			int textX = x + (BOX_WIDTH - textWidth) / 2;
			g.drawString(text, textX, y + BOX_HEIGHT - 5);
		}
		
		private void paintBox(Graphics g, int x, int y, String text, Color fill) {
			Color orginal = g.getColor();
			g.setColor(fill);
			g.fillRect(x, y, BOX_WIDTH, BOX_HEIGHT);
			g.setColor(orginal);
			paintBox(g, x, y, text);
		}
	}
}
