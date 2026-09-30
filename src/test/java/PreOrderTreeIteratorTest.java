import es.urjc.grafo.EDA.trees.LCRSTree;
import es.urjc.grafo.EDA.trees.LinkedTree;
import es.urjc.grafo.EDA.trees.NAryTree;
import es.urjc.grafo.EDA.trees.PreOrderTreeIterator;
import es.urjc.grafo.EDA.utils.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class PreOrderTreeIteratorTest {

    NAryTree<String> t;

    private void setUpTree() {
        Position<String> a = t.addRoot("A");
        Position<String> b = t.add("B", a);
        Position<String> c = t.add("C", a);
        Position<String> d = t.add("D", a);
        Position<String> e = t.add("E", c);
        Position<String> f = t.add("F", c);
        Position<String> g = t.add("G", c);
        Position<String> h = t.add("H", d);
        Position<String> i = t.add("I", f);
        Position<String> j = t.add("J", f);
    }

    @Test
    public void testIteratorLCRSTree() {
        this.t = new LCRSTree<>();
        this.setUpTree();
        StringBuilder salida = new StringBuilder();
        PreOrderTreeIterator<String> it = new PreOrderTreeIterator<>(t);
        while (it.hasNext()) {
            salida.append(it.next().getElement());
        }
        Assertions.assertEquals("ABCEFIJGDH", salida.toString());
    }

    @Test
    public void testIteratorLinkedTree() {
        this.t = new LinkedTree<>();
        this.setUpTree();
        StringBuilder salida = new StringBuilder();
        PreOrderTreeIterator <String> it = new PreOrderTreeIterator<>(t);
        while (it.hasNext()) {
            salida.append(it.next().getElement());
        }
        System.out.println(salida);
        Assertions.assertEquals("ABCEFIJGDH", salida.toString());
    }
    
}
