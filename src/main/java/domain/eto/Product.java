package domain.eto;

import domain.ProductType;

public class Product {

  private String name;
  private ProductType productType;

  public String getName() {

    return this.name;
  }

  public void setName(String name) {

    this.name = name;
  }

  public ProductType getProductType() {

    return this.productType;
  }

  public void setProductType(ProductType productType) {

    this.productType = productType;
  }

  @Override
  public boolean equals(Object o) {

    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    Product product = (Product) o;

    if (this.name != null ? !this.name.equals(product.name) : product.name != null) {
      return false;
    }
    return this.productType == product.productType;
  }

  @Override
  public int hashCode() {

    int result = this.name != null ? this.name.hashCode() : 0;
    result = 31 * result + (this.productType != null ? this.productType.hashCode() : 0);
    return result;
  }

  @Override
  public String toString() {

    return "Produce{" +
        "name='" + name + '\'' +
        ", productType=" + productType +
        '}';
  }
}
