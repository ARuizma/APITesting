package resources;
//enum is special class in java which has collections of constants or methods
public enum EnumResources {

    addPlaceAPI("maps/api/place/add/json"),
    getPlaceAPI("maps/api/place/get/json"),
    deletePlaceAPI("maps/api/place/delete/json");
    private String resource;

    EnumResources(String resource){
        this.resource=resource;
    }

    public String getResource(){
        return resource;
    }
}
