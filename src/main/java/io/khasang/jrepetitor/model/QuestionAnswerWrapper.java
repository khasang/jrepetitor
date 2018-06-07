package io.khasang.jrepetitor.model;

import java.util.ArrayList;
import java.util.List;

public class QuestionAnswerWrapper {
    private Long questionId;

    private List<SelectedItemWrapper> selectedItemWrappers;

    public QuestionAnswerWrapper() {
        selectedItemWrappers = new ArrayList<>();
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public List<SelectedItemWrapper> getSelectedItemWrappers() {
        return selectedItemWrappers;
    }

    public void setSelectedItemWrappers(List<SelectedItemWrapper> selectedItemWrappers) {
        this.selectedItemWrappers = selectedItemWrappers;
    }

    public void addSelectedItem(SelectedItemWrapper selectedItem) {
        selectedItemWrappers.add(selectedItem);
    }
}
