package es.uma.lcc.neo.cintrano.shortestpath.model;

import org.uma.jmetal.operator.SelectionOperator;

import java.util.List;

/**
 * Created by christian on 14/12/16.
 */
public class SelectionSimple implements SelectionOperator {
    public Object execute(Object o) {
        return ((List) o).get(0);
    }
}
