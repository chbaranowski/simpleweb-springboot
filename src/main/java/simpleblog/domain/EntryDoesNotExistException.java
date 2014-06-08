package simpleblog.domain;

public class EntryDoesNotExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private final Long entryId;

	public EntryDoesNotExistException(Long entryId) {
		this.entryId = entryId;
	}

	public Long getEntryId() {
		return entryId;
	}

}
