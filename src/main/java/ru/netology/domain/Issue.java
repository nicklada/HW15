package ru.netology.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.*;
import java.util.*;
import java.util.function.Predicate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Issue implements Comparable <Issue> {
    private int id;
    private boolean isOpen;
    private String Author;
    private int openedDaysAgo;
    private Set<String> issueLabels = new HashSet<>();
    private Set<String> issueAssignee = new HashSet<>();

    @Override
    public int compareTo(Issue o) {
        return openedDaysAgo - o.openedDaysAgo;
    }
}
