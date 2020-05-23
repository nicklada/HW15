package ru.netology.manager;


import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class IssueManager {
    private IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

    public void issueAdd(Issue item) {
        repository.save(item);
    }

    public Collection<Issue> getAll() {
        return repository.findAll();
    }

    public boolean addAll(Collection<Issue> items) {
        return repository.addAll(items);
    }

    public Collection<Issue> sortByNewest() {
        Comparator byNewest = Comparator.naturalOrder();
        Collection<Issue> issues = new ArrayList<>();
        issues.addAll(repository.findAll());
        ((ArrayList<Issue>) issues).sort(byNewest);
        return issues;
    }

    public Collection<Issue> sortByOldest() {
        Comparator byNewest = Comparator.reverseOrder();
        Collection<Issue> issues = new ArrayList<>();
        issues.addAll(repository.findAll());
        ((ArrayList<Issue>) issues).sort(byNewest);
        return issues;
    }
}
