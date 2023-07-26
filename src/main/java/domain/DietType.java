package domain;

public enum DietType {

  VEGETARIAN,
  REGULAR,
  VEGAN;

  public boolean compare(DietType dietType){
    if(this == VEGETARIAN && dietType == VEGAN) return true;
    return this == dietType;
  }
}
