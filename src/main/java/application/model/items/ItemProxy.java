package application.model.items;

import java.util.ArrayList;

import org.puremvc.java.multicore.interfaces.IProxy;
import org.puremvc.java.multicore.patterns.proxy.Proxy;

public class ItemProxy extends Proxy implements IProxy {

	public static final String NAME = "ItemProxy";
	
	public ItemProxy() {
		super(NAME, new ArrayList<ItemVO>());
		//addItem(new ItemVO("test"));
		System.out.println("ItemProxy()");
	}
	
	public final ArrayList<ItemVO> items() {
		return (ArrayList<ItemVO>) this.getData();
	}
	
	public final void addItem(final ItemVO itemToAdd) {
		System.out.println("  ItemProxy: addItem()");
		items().add(itemToAdd);
	}
	
	public final void deleteItem(final ItemVO itemToDelete) {
		System.out.println("  ItemProxy: deleteItem()");
		for (int i = 0; i < items().size(); i++) {
			if (items().get(i).getText().equals(itemToDelete.getText())) {
				items().remove(i);
			}
		}
	}

}
