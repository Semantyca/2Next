package com.toonext;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toonext.ftengine.ElasticSearchConfiguration;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.federecio.dropwizard.swagger.SwaggerBundleConfiguration;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class PrimaryConfiguration extends Configuration {
    @NotEmpty
    private String template;

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    private ElasticSearchConfiguration elastic = new ElasticSearchConfiguration();

    @JsonProperty("swagger")
    public SwaggerBundleConfiguration swagger;

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory factory) {
        this.database = factory;
    }

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("elastic")
    public ElasticSearchConfiguration getElastic() {
        return elastic;
    }

    @JsonProperty("elastic")
    public void setElastic(ElasticSearchConfiguration elastic) {
        this.elastic = elastic;
    }

    public SwaggerBundleConfiguration getSwagger() {
        return swagger;
    }

    public void setSwagger(SwaggerBundleConfiguration swagger) {
        this.swagger = swagger;
    }

    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }


}
