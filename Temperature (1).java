class Temperature {
    String city;
    String state;
    int lowTemp;
    int highTemp;

    public Temperature(String city, String state, int lowTemp, int highTemp) {
        this.city = city;
        this.state = state;
        this.lowTemp = lowTemp;
        this.highTemp = highTemp;
    }

    public int differential() {
        return highTemp - lowTemp;
    }

    @Override
    public String toString() {
        return city + ", " + state + ": " + lowTemp + "-" + highTemp;
    }
}
