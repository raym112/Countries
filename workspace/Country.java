public class Country {
  private String name;
  private String capital;
  private String language;
  private String imageFile;

  public Country(String name, String capital, String language, String imageFile) {
      this.name = name;
      this.capital = capital;
      this.language = language;
      this.imageFile = imageFile;
  }

  public String getName() { return name; }
  public String getCapital() { return capital; }
  public String getLanguage() { return language; }
  public String getImageFile() { return imageFile; }

  @Override
  public String toString() {
      return name + "'s capital is " + capital + " and its primary language is " + language + ".";
  }
}
