package entity;

import java.util.Date;

/**
 * Created by Ace on 2017/6/5.
 */
public class VisitedEntity {
    private long visitedCount;
    private Date visitedDay;

    public VisitedEntity() {
    }

    public VisitedEntity(long visited, Date day) {
        this.visitedCount = visited;
        this.visitedDay = day;
    }

    public long getVisitedCount() {
        return visitedCount;
    }

    public void setVisitedCount(long visitedCount) {
        this.visitedCount = visitedCount;
    }

    public Date getVisitedDay() {
        return visitedDay;
    }

    public void setVisitedDay(Date visitedDay) {
        this.visitedDay = visitedDay;
    }
}
