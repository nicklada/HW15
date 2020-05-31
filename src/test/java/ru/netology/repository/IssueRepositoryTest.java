package ru.netology.repository;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class IssueRepositoryTest {

    private IssueRepository repository = new IssueRepository();

    private Issue issue1 = new Issue(1, true, "Lada", 3, new HashSet<String>(Arrays.asList("label1", "label2", "label3")), new HashSet<String>(Arrays.asList("nicklada", "mari", "peter")));
    private Issue issue2 = new Issue(2, false, "Lada", 10, new HashSet<String>(Arrays.asList("label1", "label2", "label5")), new HashSet<String>(Arrays.asList("nicklada", "anton", "peter")));
    private Issue issue3 = new Issue(3, true, "Mike", 1, new HashSet<String>(Arrays.asList("label4", "label5", "label6")), new HashSet<String>(Arrays.asList("bob", "lisa", "sophi")));
    private Issue issue4 = new Issue(4, false, "Lina", 25, new HashSet<String>(Arrays.asList("label3", "label1", "label4")), new HashSet<String>(Arrays.asList("mari", "lisa", "ani")));

    @Nested
    public class EmptyRepository {
        @Test
        void shouldReturnEmptyWhenFindByOpen() {
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyWhenFindByClosed() {
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = repository.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmpryIfClose() {
            repository.closeById(1);
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfOpen() {
            repository.openById(2);
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class SingleItem {

        @Test
        void shouldFindOpenIfOpen() {
            repository.save(issue1);
            List<Issue> expected = new ArrayList<>(List.of(issue1));
            List<Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoOpen() {
            repository.save(issue2);
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindClosedIfClosed() {
            repository.save(issue2);
            List<Issue> expected = new ArrayList<>(List.of(issue2));
            List<Issue> actual = repository.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoClosed() {
            repository.save(issue3);
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = repository.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void shouldClose() {
            repository.save(issue1);
            repository.closeById(1);
            List<Issue> expected = new ArrayList<>(List.of(issue1));
            List<Issue> actual = repository.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void shouldDoNothingIfWrongIdWhenClose() {
            repository.save(issue1);
            repository.closeById(3);
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = repository.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void shouldDoNothingIfNothingToClose() {
            repository.save(issue2);
            repository.closeById(2);
            List<Issue> expected = new ArrayList<>(List.of(issue2));
            List<Issue> actual = repository.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void shouldOpen() {
            repository.save(issue2);
            repository.openById(2);
            List<Issue> expected = new ArrayList<>(List.of(issue2));
            List<Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldDoNothingIfWrongIdWhenOpen() {
            repository.save(issue1);
            repository.openById(5);
            List<Issue> expected = new ArrayList<>(List.of(issue1));
            List<Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldDoNothingIfNothingToOpen() {
            repository.save(issue1);
            repository.openById(1);
            List<Issue> expected = new ArrayList<>(List.of(issue1));
            List<Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }
    }

    @Nested
    public class MultipleItems {

        @Test
        void shouldFindOpenIfOpen() {
            repository.addAll(List.of(issue1,issue2,issue3,issue4));
            List<Issue> expected = new ArrayList<>(List.of(issue1, issue3));
            List<Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoOpen() {
            repository.addAll(List.of(issue2,issue4));
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldFindClosedIfClosed() {
            repository.addAll(List.of(issue1,issue2,issue3,issue4));
            List<Issue> expected = new ArrayList<>(List.of(issue2, issue4));
            List<Issue> actual = repository.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void shouldReturnEmptyIfNoClosed() {
            repository.addAll(List.of(issue1,issue3));
            List<Issue> expected = new ArrayList<>();
            List<Issue> actual = repository.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void shouldClose() {
            repository.addAll(List.of(issue1,issue2,issue3,issue4));
            repository.closeById(1);
            List<Issue> expected = new ArrayList<>(List.of(issue1, issue2, issue4));
            List<Issue> actual = repository.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void shouldDoNothingIfWrongIdWhenClose() {
            repository.addAll(List.of(issue1,issue2,issue3,issue4));
            repository.closeById(5);
            List<Issue> expected = new ArrayList<>(List.of(issue2, issue4));
            List<Issue> actual = repository.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void shouldDoNothingIfNothingToClose() {
            repository.addAll(List.of(issue2,issue4));
            repository.closeById(4);
            List<Issue> expected = new ArrayList<>(List.of(issue2, issue4));
            List<Issue> actual = repository.findClosed();
            assertEquals(expected, actual);
        }

        @Test
        void shouldOpen() {
            repository.addAll(List.of(issue2,issue4));
            repository.openById(4);
            List<Issue> expected = new ArrayList<>(List.of(issue4));
            List<Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldDoNothingIfWrongIdWhenOpen() {
            repository.addAll(List.of(issue1,issue2,issue3,issue4));
            repository.openById(5);
            List<Issue> expected = new ArrayList<>(List.of(issue1, issue3));
            List<Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }

        @Test
        void shouldDoNothingIfNothingToOpen() {
            repository.addAll(List.of(issue1,issue3));
            repository.openById(1);
            List<Issue> expected = new ArrayList<>(List.of(issue1, issue3));
            List<Issue> actual = repository.findOpen();
            assertEquals(expected, actual);
        }
    }
}
