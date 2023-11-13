import java.util.List;

public class ProgramaNetflix {
    private String id;
    private String titulo;
    private String showType;
    private String descricao;
    private int releaseYear;
    private String ageCertification;
    private int runtime;
    private List<String> generos;
    private List<String> productionCountries;
    private Double temporadas;
    private String imdbId;
    private double imdbScore;
    private double imdbVotes;
    private double tmdbPopularity;
    private double tmdbScore;

    public ProgramaNetflix(String id, String titulo, String showType, String descricao, int releaseYear,
            String ageCertification, int runtime, List<String> generos, List<String> productionCountries,
            double temporadas, String imdbId, double imdbScore, double imdbVotes, double tmdbPopularity,
            double tmdbScore) {
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
    public String getId() {
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

    public List<String> getGeneros() {
        return generos;
    }

    public List<String> getProductionCountries() {
        return productionCountries;
    }

    public double getTemporadas() {
        return temporadas;
    }

    public String getImdbId() {
        return imdbId;
    }

    public double getImdbScore() {
        return imdbScore;
    }

    public double getImdbVotes() {
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

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public void setProductionCountries(List<String> productionCountries) {
        this.productionCountries = productionCountries;
    }

    public void setTemporadas(double temporadas) {
        this.temporadas = temporadas;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public void setImdbScore(double imdbScore) {
        this.imdbScore = imdbScore;
    }

    public void setImdbVotes(double imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public void setTmdbPopularity(double tmdbPopularity) {
        this.tmdbPopularity = tmdbPopularity;
    }

    public void setTmdbScore(double tmdbScore) {
        this.tmdbScore = tmdbScore;
    }

    @Override
    public String toString() {
        return "Programa Netflix ID: " + id + "\n" +
                "Título: " + titulo + "\n" +
                "Tipo: " + showType + "\n" +
                "Descrição: " + descricao + "\n" +
                "Ano de Lançamento: " + releaseYear + "\n" +
                "Classificação Etária: " + ageCertification + "\n" +
                "Duração: " + runtime + " minutos\n" +
                "Gêneros: " + generos + "\n" +
                "Países de Produção: " + productionCountries + "\n" +
                "Número de Temporadas: " + temporadas + "\n" +
                "ID do IMDB: " + imdbId + "\n" +
                "Pontuação do IMDB: " + imdbScore + "\n" +
                "Votos no IMDB: " + imdbVotes + "\n" +
                "Popularidade no TMDB: " + tmdbPopularity + "\n" +
                "Pontuação no TMDB: " + tmdbScore + "\n";
    }

}
