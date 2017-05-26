package util;

/**
 * Created by Ace on 2017/5/26.
 */
public class Page {
    private int begin;
    private int end;
    private int pages;

    public Page() {
    }

    public Page(int begin, int end, int pages) {
        this.begin = begin;
        this.end = end;
        this.pages = pages;
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(int begin) {
        this.begin = begin;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

}

