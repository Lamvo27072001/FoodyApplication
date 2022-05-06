package View.Fragments;

public class Food {
    String storeId;
    String locationName;
    String name;
    String address;
    String photoUrl;
    public Food(String storeId, String locationName, String name, String address, String photoUrl) {
        this.storeId = storeId;
        this.locationName = locationName;
        this.name = name;
        this.address = address;
        this.photoUrl = photoUrl;
    }
    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getAddress() {
        return address;
    }


}
