public class ProgramaNetflix {
    private int id;
    private String titulo;
    private String showType;
    private String descricao;
    private int releaseYear;
    private String ageCertification;
    private int runtime;
    private String generos;
    private String productionCountries;
    private int temporadas;
    private String imdbId;
    private double imdbScore;
    private int imdbVotes;
    private double tmdbPopularity;
    private double tmdbScore;

    public ProgramaNetflix(int id, String titulo, String showType, String descricao, int releaseYear,
            String ageCertification, int runtime, String generos, String productionCountries, int temporadas,
            String imdbId, double imdbScore, int imdbVotes, double tmdbPopularity, double tmdbScore) {
        this.id = id;
        this.titulo = titulo;
        this.showType = showType;
        this.descricao = descricao;
        this.releaseYear = releaseYear;
        this.ageCertification = ageCertification;
        this.runtime = runtime;
        this.generos = generos;
        this.productionCountries = productionCountries;
        this.temporadas = temporadas;
        this.imdbId = imdbId;
        this.imdbScore = imdbScore;
        this.imdbVotes = imdbVotes;
        this.tmdbPopularity = tmdbPopularity;
        this.tmdbScore = tmdbScore;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getShowType() {
        return showType;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getAgeCertification() {
        return ageCertification;
    }

    public int getRuntime() {
        return runtime;
    }

    public String getGeneros() {
        return generos;
    }

    public String getProductionCountries() {
        return productionCountries;
    }

    public int getTemporadas() {
        return temporadas;
    }

    public String getImdbId() {
        return imdbId;
    }

    public double getImdbScore() {
        return imdbScore;
    }

    public int getImdbVotes() {
        return imdbVotes;
    }

    public double getTmdbPopularity() {
        return tmdbPopularity;
    }

    public double getTmdbScore() {
        return tmdbScore;
    }

    // Setters
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setAgeCertification(String ageCertification) {
        this.ageCertification = ageCertification;
    }

    public void setRuntime(int runtime) {
        this.runtime = runtime;
    }

    public void setGeneros(String generos) {
        this.generos = generos;
    }

    public void setProductionCountries(String productionCountries) {
        this.productionCountries = productionCountries;
    }

    public void setTemporadas(int temporadas) {
        this.temporadas = temporadas;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public void setImdbScore(double imdbScore) {
        this.imdbScore = imdbScore;
    }

    public void setImdbVotes(int imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public void setTmdbPopularity(double tmdbPopularity) {
        this.tmdbPopularity = tmdbPopularity;
    }

    public void setTmdbScore(double tmdbScore) {
        this.tmdbScore = tmdbScore;
    }
}
