package com.toonext;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.toonext.client.JerseyClientConfiguration;
import com.toonext.ftengine.ElasticSearchConfiguration;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class DefaultConfiguration extends Configuration {

    private String template;

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    private ElasticSearchConfiguration elastic = new ElasticSearchConfiguration();

    private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();

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


    @JsonProperty
    public String getTemplate() {
        return template;
    }

    @JsonProperty
    public void setTemplate(String template) {
        this.template = template;
    }


    public Object getJerseyClientConfiguration() {
        return jerseyClient;
    }
}
