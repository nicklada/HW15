package ru.netology.manager;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {

    IssueRepository repository = new IssueRepository();
    IssueManager manager = new IssueManager(repository);

    Issue issue1 = new Issue(1, true, "Lada", 3, new HashSet<String>(Arrays.asList("label1", "label2", "label3")), new HashSet<String>(Arrays.asList("nicklada", "mari", "peter")));
    Issue issue2 = new Issue(2, false, "Lada", 10, new HashSet<String>(Arrays.asList("label1", "label2", "label5")), new HashSet<String>(Arrays.asList("nicklada", "anton", "peter")));
    Issue issue3 = new Issue(3, true, "Mike", 1, new HashSet<String>(Arrays.asList("label4", "label5", "label6")), new HashSet<String>(Arrays.asList("bob", "lisa", "sophi")));


    @Nested
    public class Empty{

        @Test
        void shouldReturnEmptyIfNothingToSortByNewest() {
            manager.addAll(List.of());
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = manager.sortByNewest();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNothingToSortByOldest() {
            manager.addAll(List.of());
            Collection <Issue> expected = new ArrayList<>();
            Collection <Issue> actual = manager.sortByOldest();
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class SingleItem {

        @Test
        void shouldReturnOneToSortByNewest() {
            manager.addAll(List.of(issue1));
            Collection <Issue> expected = new ArrayList<>();
            expected.add(issue1);
            Collection <Issue> actual = manager.sortByNewest();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnOneToSortByOldest() {
            manager.addAll(List.of(issue1));
            Collection <Issue> expected = new ArrayList<>();
            expected.add(issue1);
            Collection <Issue> actual = manager.sortByOldest();
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class MultipleItems{

        @Test
        void shoulAddByOne() {
            manager.issueAdd(issue1);
            manager.issueAdd(issue2);
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue1,issue2));
            Collection <Issue> actual = manager.getAll();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByNewest() {
            manager.addAll(List.of(issue1,issue2,issue3));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue3,issue1,issue2));
            Collection <Issue> actual = manager.sortByNewest();
            assertEquals(expected, actual);
        }

        @Test
        void shouldSortByOldest() {
            manager.addAll(List.of(issue1,issue2,issue3));
            Collection <Issue> expected = new ArrayList<>();
            expected.addAll(List.of(issue2,issue1,issue3));
            Collection <Issue> actual = manager.sortByOldest();
            assertEquals(expected, actual);
        }
    }
}