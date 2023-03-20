package sphabucks.products.vo;

public interface IResponseSearchResult {
    Long getId();
    String getName();
    String getPrice();
    String getSize();
    String getImage();
    Long getEventId();
    Long getBigCategoryId();
    Long getSmallCategoryId();

}
