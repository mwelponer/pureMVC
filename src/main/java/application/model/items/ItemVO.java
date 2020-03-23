package application.model.items;


public class ItemVO {

	private String text;
	
	public ItemVO(String name) {
		this.text = name;
	}
	
	public String getText() {
		return this.text;
	}
}
