package model;

public class Category {
    private int categoryNo;
    private String categoryName;
    private String categoryDetails;

    public int getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(int categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryDetails() {
        return categoryDetails;
    }

    public void setCategoryDetails(String categoryDetails) {
        this.categoryDetails = categoryDetails;
    }

    public Category(int categoryNo, String categoryName) {
        this.categoryNo = categoryNo;
        this.categoryName = categoryName;
    }

    public Category() {

    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryNo=" + categoryNo +
                ", categoryName='" + categoryName + '\'' +
                ", categoryDetails='" + categoryDetails + '\'' +
                '}';
    }
}
