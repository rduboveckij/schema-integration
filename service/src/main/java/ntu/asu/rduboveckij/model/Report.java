package ntu.asu.rduboveckij.model;

import com.google.common.collect.Sets;
import ntu.asu.rduboveckij.model.external.Model;
import ntu.asu.rduboveckij.model.internal.Mapping;
import ntu.asu.rduboveckij.model.internal.Result;

import java.io.Serializable;
import java.util.Set;

/**
 * @author andrus.god
 * @since 27.11.2014.
 */
public class Report implements Serializable {
    private final Model firstModel;
    private final Model secondModel;
    private Set<Result.Element> syntacticElements = Sets.newHashSet();
    private Set<Result.Element> dictionaryElements = Sets.newHashSet();
    private Set<Result.Element> dataTypeElements = Sets.newHashSet();
    private Set<Result.Element> mainElements = Sets.newHashSet();
    private transient Mapping mapping;

    public Report(Model firstModel, Model secondModel) {
        this.firstModel = firstModel;
        this.secondModel = secondModel;
    }

    public Model getFirstModel() {
        return firstModel;
    }

    public Model getSecondModel() {
        return secondModel;
    }

    public Set<Result.Element> getSyntacticElements() {
        return syntacticElements;
    }

    public void setSyntacticElements(Set<Result.Element> syntacticElements) {
        this.syntacticElements = syntacticElements;
    }

    public Set<Result.Element> getDictionaryElements() {
        return dictionaryElements;
    }

    public void setDictionaryElements(Set<Result.Element> dictionaryElements) {
        this.dictionaryElements = dictionaryElements;
    }

    public Set<Result.Element> getDataTypeElements() {
        return dataTypeElements;
    }

    public void setDataTypeElements(Set<Result.Element> dataTypeElements) {
        this.dataTypeElements = dataTypeElements;
    }

    public Set<Result.Element> getMainElements() {
        return mainElements;
    }

    public void setMainElements(Set<Result.Element> mainElements) {
        this.mainElements = mainElements;
    }

    public Mapping getMapping() {
        return mapping;
    }

    public void setMapping(Mapping mapping) {
        this.mapping = mapping;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        if (dataTypeElements != null ? !dataTypeElements.equals(report.dataTypeElements) : report.dataTypeElements != null)
            return false;
        if (dictionaryElements != null ? !dictionaryElements.equals(report.dictionaryElements) : report.dictionaryElements != null)
            return false;
        if (firstModel != null ? !firstModel.equals(report.firstModel) : report.firstModel != null) return false;
        if (mainElements != null ? !mainElements.equals(report.mainElements) : report.mainElements != null)
            return false;
        if (mapping != null ? !mapping.equals(report.mapping) : report.mapping != null) return false;
        if (secondModel != null ? !secondModel.equals(report.secondModel) : report.secondModel != null) return false;
        if (syntacticElements != null ? !syntacticElements.equals(report.syntacticElements) : report.syntacticElements != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = firstModel != null ? firstModel.hashCode() : 0;
        result = 31 * result + (secondModel != null ? secondModel.hashCode() : 0);
        result = 31 * result + (syntacticElements != null ? syntacticElements.hashCode() : 0);
        result = 31 * result + (dictionaryElements != null ? dictionaryElements.hashCode() : 0);
        result = 31 * result + (dataTypeElements != null ? dataTypeElements.hashCode() : 0);
        result = 31 * result + (mainElements != null ? mainElements.hashCode() : 0);
        result = 31 * result + (mapping != null ? mapping.hashCode() : 0);
        return result;
    }
}