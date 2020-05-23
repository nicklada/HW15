package ru.netology.repository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import ru.netology.domain.Issue;

import java.util.*;
import java.util.function.Predicate;

@AllArgsConstructor
@NoArgsConstructor
public class IssueRepository {
    private Collection<Issue> items = new ArrayList<>();

    public boolean addAll(Collection<Issue> items) {
        return this.items.addAll(items);
    }

    public Collection<Issue> findAll() {
        return items;
    }

    public boolean save(Issue item) {
        return items.add(item);
    }

    public Collection<Issue> findOpen() {
        Collection<Issue> issues = new ArrayList<>();
        for (Issue item : items)
            if (item.isOpen()) issues.add(item);
        return issues;
    }

    public Collection<Issue> findClosed() {
        Collection<Issue> issues = new ArrayList<>();
        for (Issue item : items)
            if (!item.isOpen()) issues.add(item);
        return issues;
    }

    public Collection<Issue> findByAuthor(String author) {
        Predicate<String> byAuthor = t -> t.equals(author);
        Collection<Issue> issues = new ArrayList<>();
        for (Issue item : items)
            if (byAuthor.test(item.getAuthor())) issues.add(item);
        return issues;
    }

    public Collection<Issue> findByLabel(Set<String> label) {
        Predicate<Set<String>> byLabel = t -> t.containsAll(label);
        Collection<Issue> issues = new ArrayList<>();
        for (Issue item : items)
            if (byLabel.test(item.getIssueLabels())) {
                issues.add(item);
            }
        return issues;
    }

    public Collection<Issue> findByAssignee(Set<String> assignee) {
        Predicate<Set<String>> byLabel = t -> t.containsAll(assignee);
        Collection<Issue> issues = new ArrayList<>();
        for (Issue item : items)
            if (byLabel.test(item.getIssueAssignee())) {
                issues.add(item);
            }
        return issues;
    }


    public boolean closeById(int id) {
        for (Issue item : items) {
            if (item.getId() == id && item.isOpen()) {
                item.setOpen(false);
                return item.isOpen();
            }
        }
        return false;
    }

    public boolean openById(int id) {
        for (Issue item : items) {
            if (item.getId() == id && !item.isOpen()) {
                item.setOpen(true);
                return item.isOpen();
            }
        }
        return false;
    }
}
