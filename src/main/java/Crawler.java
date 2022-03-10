import java.net.URI;

public  class Crawler implements Browseable {

	URI currentPage;

	@Override
	public void visit(URI uri){
		this.currentPage = uri;
	}

	@Override
	public URI getCurrentPage() {
		return currentPage;
	}
}