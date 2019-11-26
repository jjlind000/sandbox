/**
 * Created by jasonl on 04/09/19
 */
public class GenericsTest
{
    public static void main(String...args){

        Box<String> box = new BoxImpl<>();

    }

}


interface Box<T> {
    public T get();
    public void put(T element);
}

class BoxImpl<X> implements Box<X>{


    @Override
    public X get()
    {
        return null;
    }

    @Override
    public void put(X element)
    {

    }
}
