import java.net.URI;

public interface Browseable {
	public void visit(URI uri);

	public URI getCurrentPage();
}