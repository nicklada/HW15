package ru.netology.manager;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {

    private IssueRepository repository = new IssueRepository();
    private IssueManager manager = new IssueManager(repository);

    private Issue issue1 = new Issue(1, true, "Lada", 3, new HashSet<String>(Arrays.asList("label1", "label2", "label3")), new HashSet<String>(Arrays.asList("nicklada", "mari", "peter")));
    private Issue issue2 = new Issue(2, false, "Lada", 10, new HashSet<String>(Arrays.asList("label1", "label2", "label5")), new HashSet<String>(Arrays.asList("nicklada", "anton", "peter")));
    private Issue issue3 = new Issue(3, true, "Mike", 1, new HashSet<String>(Arrays.asList("label4", "label5", "label6")), new HashSet<String>(Arrays.asList("bob", "lisa", "sophi")));
    private Issue issue4 = new Issue(4, false, "Lina", 25, new HashSet<String>(Arrays.asList("label3", "label1", "label4")), new HashSet<String>(Arrays.asList("mari", "lisa", "ani")));

    @Nested
    public class Empty {

        @Test
        void shouldReturnEmptyIfNothingToSortByNewest() {
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = manager.sortByNewest();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNothingToSortByOldest() {
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = manager.sortByOldest();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyWhenFindByAuthor() {
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = manager.findByAuthor("Lada");
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyWhenFindByLabel() {
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = manager.findByLabel( new HashSet<String>(Arrays.asList("label1")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyWhenFindByAssignee() {
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = manager.findByAssignee( new HashSet<String>(Arrays.asList("nicklada")));
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class SingleItem {

        @Test
        void shouldReturnOneToSortByNewest() {
            manager.issueAdd(issue1);
            List<Issue> expected = new ArrayList<>(List.of(issue1));
            List<Issue> actual = manager.sortByNewest();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnOneToSortByOldest() {
            manager.issueAdd(issue1);
            List<Issue> expected = new ArrayList<>(List.of(issue1));
            List<Issue> actual = manager.sortByOldest();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindByAuthor() {
            manager.issueAdd(issue1);
            Collection<Issue> expected = new ArrayList<>(List.of(issue1));
            Collection <Issue> actual = manager.findByAuthor("Lada");
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoAuthor() {
            manager.issueAdd(issue1);
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = manager.findByAuthor("Bob");
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindByLabel() {
            manager.issueAdd(issue3);
            Collection<Issue> expected = new ArrayList<>(List.of(issue3));
            Collection <Issue> actual = manager.findByLabel( new HashSet<String>(Arrays.asList("label4")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoLabel() {
            manager.issueAdd(issue1);
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = manager.findByLabel( new HashSet<String>(Arrays.asList("label25")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindByAssignee() {
            manager.issueAdd(issue1);
            Collection<Issue> expected = new ArrayList<>(List.of(issue1));
            Collection <Issue> actual = manager.findByAssignee( new HashSet<String>(Arrays.asList("nicklada")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyWhenFindByAssignee() {
            manager.issueAdd(issue1);
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = manager.findByAssignee( new HashSet<String>(Arrays.asList("emma")));
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class MultipleItems {

        @Test
        void shouldAddByOne() {
            manager.issueAdd(issue1);
            manager.issueAdd(issue2);
            List<Issue> expected = new ArrayList<>(List.of(issue1, issue2));
            List<Issue> actual = manager.getAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByNewest() {
            manager.addAll(List.of(issue1, issue2, issue3));
            List<Issue> expected = new ArrayList<>(List.of(issue3, issue1, issue2));
            List<Issue> actual = manager.sortByNewest();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByOldest() {
            manager.addAll(List.of(issue1, issue2, issue3));
            List<Issue> expected = new ArrayList<>(List.of(issue2, issue1, issue3));
            List<Issue> actual = manager.sortByOldest();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindByAuthor() {
            manager.addAll(List.of(issue1, issue2, issue3, issue4));
            List<Issue> expected = new ArrayList<>(List.of(issue1, issue2));
            List<Issue> actual = manager.findByAuthor("Lada");
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoAuthor() {
            manager.addAll(List.of(issue1, issue2, issue3, issue4));
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = manager.findByAuthor("Bob");
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindByLabel() {
            manager.addAll(List.of(issue1, issue2, issue3, issue4));
            List<Issue> expected = new ArrayList<>(List.of(issue1, issue4));
            List<Issue> actual = manager.findByLabel(new HashSet<String>(Arrays.asList("label3")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoLabel() {
            manager.addAll(List.of(issue1, issue2, issue3, issue4));
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = manager.findByLabel(new HashSet<String>(Arrays.asList("label25")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindByAssignee() {
            manager.addAll(List.of(issue1, issue2, issue3, issue4));
            List<Issue> expected = new ArrayList<>(List.of(issue3, issue4));
            List<Issue> actual = manager.findByAssignee(new HashSet<String>(Arrays.asList("lisa")));
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyWhenFindByAssignee() {
            manager.addAll(List.of(issue1, issue2, issue3, issue4));
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = manager.findByAssignee(new HashSet<String>(Arrays.asList("Emma")));
            assertEquals(expected, actual);
        }
    }
}