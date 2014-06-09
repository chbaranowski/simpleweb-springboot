package simpleblog.domain

class EntryDoesNotExistException extends RuntimeException {

	final Long entryId;

	EntryDoesNotExistException(Long entryId) {
		this.entryId = entryId;
	}

}
