import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import org.junit.Before;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Tester {

    KnapSack sack;
    ArrayList<Integer> list;

    @Before
    public void setClass()throws Exception{
        sack = new KnapSack(new Scanner(new File("TestFile")));
        list = new ArrayList<>();
    }

    @Test
    public void testBound(){
        int bound = sack.test(list);
        //Starting bound
        assertThat(bound, is(219));
        //dont use 1
        list.add(1);
        bound = sack.test(list);
        assertThat(bound, is(205));
        list.remove(0);
        //use 1
        bound = sack.test(list);
        assertThat(bound, is(219));
        //use 1 no 2
        list.add(2);
        bound = sack.test(list);
        assertThat(bound, is(203));
        //use 1, 2
        list.remove(0);
        bound = sack.test(list);
        assertThat(bound, is(219));
        //use 1, 2 no 3
        list.add(3);
        bound = sack.test(list);
        assertThat(bound, is(213));
        //use 1, 2 no 3, 4
        list.add(4);
        bound = sack.test(list);
        assertThat(bound, is(197));
        //use 1, 2, 4 no 3
        list.remove(1);
        bound = sack.test(list);
        assertThat(bound, is(213));
        //use 1, 2, 4 no 3 ,5
        list.add(5);
        bound = sack.test(list);
        assertThat(bound, is(212));
    }
}
