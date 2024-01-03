package entities;

public class Localizacao {
    private double latitute, longitude;

    public Localizacao(double latitute, double longitude) {
        this.latitute = latitute;
        this.longitude = longitude;
    }

    public Localizacao() {}

    public double getLatitute() {
        return latitute;
    }

    public void setLatitute(double latitute) {
        this.latitute = latitute;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
