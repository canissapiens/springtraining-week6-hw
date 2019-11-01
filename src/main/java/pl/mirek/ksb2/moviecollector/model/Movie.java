package pl.mirek.ksb2.moviecollector.model;

public class Movie {
    private String title;
    private String year;
    private String producer;

    public Movie() {
    }

    public Movie(String title, String year, String producer) {
        this.title = title;
        this.year = year;
        this.producer = producer;
    }


    @Override
    public String toString() {
        return "Movie{" +
                "title='" + title + '\'' +
                ", year=" + year +
                ", producer='" + producer + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getProducer() {
        return producer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }
}
