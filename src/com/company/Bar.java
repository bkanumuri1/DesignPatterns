package com.company;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A decorator for the line graph, extends the Decorator class
 * @author Haritej Lakshmi Narayan , Bhavana Priya Kanumuri
 */
public class Bar extends Decorator{
    @Override
    /**
     * method inherited from parent class(Decorator) to create a new JPanel
     * @return new panel
     */
    public JPanel draw(){    
        return new Bar();
    }
    /**
     * Plots the graph in panel
     * @param g creates the bar in graph
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - 2) / (data.getData().size() - 1);
        double yScale = ((double) getHeight() - 2) / (MAX_SCORE - 1);
        List<Point> graphPoints = new ArrayList<Point>();
        for (int i = 0; i < data.getData().size(); i++) {
            int x1 = (int) (i * xScale);
            int y1 = (int) ((MAX_SCORE - data.getData().get(i)) * yScale);
            graphPoints.add(new Point(x1, y1));
        }
        Stroke oldStroke = g2.getStroke();
        g2.setColor(GRAPH_COLOR);
        g2.setStroke(GRAPH_STROKE);
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            g2.setColor(GRAPH_POINT_COLOR);
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            int ovalW = GRAPH_POINT_WIDTH;
            int ovalH = GRAPH_POINT_WIDTH;
            g2.drawLine(x1, y1, x2, y2);
            x1 -= GRAPH_POINT_WIDTH / 2;
            y1 -= GRAPH_POINT_WIDTH / 2;;
            g2.fillRect(x1, y1, ovalW, ovalH);
            g2.setColor(GRAPH_BAR_COLOR);
            g2.fillRect(x1, y1+ovalH, ovalW, PREF_H);
        }
        g2.setStroke(oldStroke);
        g2.setColor(GRAPH_AVG_LINE);
        g.drawLine(0,AverageEvaluator.getAverageEvaluator().calculate(graphPoints), PREF_W, AverageEvaluator.getAverageEvaluator().calculate(graphPoints));
    }
}
