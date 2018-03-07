package de.scout24.webanalyzerrest.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import de.scout24.webanalyzerrest.model.enums.ResponseItemType;
import org.hibernate.annotations.Target;

import javax.persistence.*;

@Entity
@JsonAutoDetect
public class AnalysisItemInteger extends AnalysisItem<Integer> {

    @Enumerated(value = EnumType.ORDINAL)
    private ResponseItemType itemType;
    @ManyToOne
    private AnalysisOutput output;
    @Target(value = Integer.class)
    @Column(name = "resultTypeInteger")
    private Integer resultType;

    public AnalysisItemInteger(Integer result, ResponseItemType itemType) {
        this.setResultType(result);
        this.setItemType(itemType);
    }

    public ResponseItemType getItemType() {
        return itemType;
    }

    @Override
    protected void setItemType(ResponseItemType itemType) {
        this.itemType = itemType;
    }

    @Override
    public Integer getResultType() {
        return resultType;
    }

    @Override
    protected void setResultType(Integer resultType) {
        this.resultType = resultType;
    }

    @Override
    public void setOutput(AnalysisOutput output) {
        this.output = output;
    }
}
