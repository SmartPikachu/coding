package cn.jjx.coding.springboot.mockito;



import org.junit.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class MockException {

    class MyDictionary {
        private Map<String, String> wordMap = new HashMap<>();

        public void add(String word, String meaning) {
            wordMap.put(word, meaning);
        }

        public String getMeaning(String word) {
            return wordMap.get(word);
        }
    }

    //这块是用junit4.0来实现的
    @Test(expected = NullPointerException.class)
    public void whenConfigNonVoidRetunMethodToThrowEx_thenExIsThrown() {
        MyDictionary dictMock = mock(MyDictionary.class);
        when(dictMock.getMeaning(anyString()))
                .thenThrow(NullPointerException.class);

        dictMock.getMeaning("word");
    }

    @Test(expected = IllegalStateException.class)
    public void whenConfigVoidRetunMethodToThrowEx_thenExIsThrown() {
        MyDictionary dictMock = mock(MyDictionary.class);
        doThrow(IllegalStateException.class)
                .when(dictMock)
                .add(anyString(), anyString());

        dictMock.add("word", "meaning");
    }

    @Test(expected = NullPointerException.class)
    public void whenConfigNonVoidReturnMethodToThrowExWithNewExObj_thenExIsThrown() {
        MyDictionary dictMock = mock(MyDictionary.class);
        when(dictMock.getMeaning(anyString()))
                .thenThrow(new NullPointerException("Error occurred"));

        dictMock.getMeaning("word");
    }

    @Test(expected = IllegalStateException.class)
    public void whenConfigVoidReturnMethodToThrowExWithNewExObj_thenExIsThrown() {
        MyDictionary dictMock = mock(MyDictionary.class);
        doThrow(new IllegalStateException("Error occurred"))
                .when(dictMock)
                .add(anyString(), anyString());

        dictMock.add("word", "meaning");
    }

    @Test(expected = NullPointerException.class)
    public void givenSpy_whenConfigNonVoidReturnMethodToThrowEx_thenExIsThrown() {
        MyDictionary dict = new MyDictionary();
        MyDictionary spy = Mockito.spy(dict);
        when(spy.getMeaning(anyString()))
                .thenThrow(NullPointerException.class);

        spy.getMeaning("word");
    }

}
