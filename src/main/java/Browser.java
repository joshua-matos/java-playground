import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Browser {
	private URI currentPage;
	private ArrayList<String> history;
	private LocalDateTime visitedAt;
	private HashMap<String, Integer> visitCount;
	private boolean on;
}