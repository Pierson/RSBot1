package utils;

import org.powerbot.script.lang.Filter;
import org.powerbot.script.methods.MethodContext;
import org.powerbot.script.methods.MethodProvider;
import org.powerbot.script.util.Condition;
import org.powerbot.script.wrappers.Component;
import org.powerbot.script.wrappers.Item;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Callable;


public class SelectItem extends MethodProvider {
    public static final int DATA_WIDGET = 1370;
    public static final int ITEM_WIDGET = 1371;

    public SelectItem(final MethodContext factory) {
        super(factory);
    }

    public boolean isValid() {
        return ctx.widgets.get(DATA_WIDGET).isValid() && ctx.widgets.get(ITEM_WIDGET).isValid();
    }

    public boolean close() {
        if (isValid()) {
            final Component w = ctx.widgets.get(DATA_WIDGET, 30);
            return w.isValid() && w.click(true);
        }
        return false;
    }

    public boolean changeSelectedTool() {
        final Component w = ctx.widgets.get(ITEM_WIDGET, 74);
        return w.isValid() && w.click(true);
    }

    public double getExpGain() {
        final Component w = ctx.widgets.get(DATA_WIDGET, 52);
        if (w.isValid()) {
            return Double.parseDouble(w.getText().replace("xp", ""));
        }
        return 0;
    }

    public double getSecondaryExpGain() {
        final Component w = ctx.widgets.get(DATA_WIDGET, 65);
        if (w.isValid()) {
            if (w.getText().contains("-")) {
                return 0;
            }
            return Double.parseDouble(w.getText().replace("xp", ""));
        }
        return 0;
    }

    public int getStoreValue() {
        final Component w = ctx.widgets.get(DATA_WIDGET, 66);
        if (w.isValid()) {
            if (w.getText().contains("-")) {
                return 0;
            }
            return Integer.parseInt(w.getText());
        }
        return 0;
    }

    public int getGeValue() {
        final Component w = ctx.widgets.get(DATA_WIDGET, 68);
        if (w.isValid()) {
            if (w.getText().contains("-")) {
                return 0;
            }
            return Integer.parseInt(w.getText());
        }
        return 0;
    }

    public int getAlchValue() {
        final Component w = ctx.widgets.get(DATA_WIDGET, 70);
        if (w.isValid()) {
            if (w.getText().contains("-")) {
                return 0;
            }
            return Integer.parseInt(w.getText());
        }
        return 0;
    }

    public boolean requirementsMet() {
        final Component w = ctx.widgets.get(DATA_WIDGET, 57);
        if (w.isValid()) {
            for (final Component wc : w.getChildren()) {
                if (wc.getTextureId() == 13166) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public boolean haveMaterials() {
        final Component w = ctx.widgets.get(DATA_WIDGET, 59);
        if (w.isValid()) {
            for (final Component wc : w.getChildren()) {
                if (wc.getTextureId() == 13166) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public int getCount() {
        if (isValid()) {
            return Integer.parseInt(ctx.widgets.get(DATA_WIDGET, 73).getText());
        }
        return 0;
    }

    public Component getExecuteComponent() {
        return ctx.widgets.get(DATA_WIDGET, 38);
    }

    public boolean execute() {
        if (isValid()) {
            return getExecuteComponent().click(true);
        }
        return false;
    }

    public boolean decreaseCount() {
        if (isValid()) {
            return ctx.widgets.get(ITEM_WIDGET, 26).click(true);
        }
        return false;
    }

    public boolean increaseCount() {
        if (isValid()) {
            return ctx.widgets.get(ITEM_WIDGET, 27).click(true);
        }
        return false;
    }

    public boolean setCount(final int count) {
        if (isValid()) {
            final Component enterCount = ctx.widgets.get(DATA_WIDGET, 1);
            if (enterCount.isValid() && ctx.widgets.get(DATA_WIDGET, 72).click(true)) {
                if (Condition.wait(new Callable<Boolean>() {
                    @Override
                    public Boolean call() throws Exception {
                        return ctx.widgets.get(DATA_WIDGET, 71).isValid();
                    }
                }, 10, 300)) {
                    ctx.keyboard.send(String.valueOf(count), false);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean canMake() {
        return canMake(getSelectedItem());
    }

    public boolean canMake(final Item item) {
        return canMake(item.getId());
    }

    public boolean canMake(final int id) {
        final Item item = getItem(id);
        if (!isValid() || item == null) {
            return false;
        }
        final Component[] slots = ctx.widgets.get(ITEM_WIDGET, 44).getChildren();
        for (int i = 0; i < slots.length; i++) {
            final Component slot = slots[i];
            if (slot != null && slot.getItemId() != -1) {
                if (slot.getItemId() == id) {
                    final int textureId = slots[i - 2].getTextureId();
                    return textureId != 13987 && textureId != 13988;
                }
            }
        }
        return false;
    }

    public boolean selectItem(final Item item) {
        return selectItem(item.getId());
    }

    public boolean selectItem(final int id) {
        final Item item = getItem(id);
        if (!isValid() || item == null) {
            return false;
        }
        if (item.equals(getSelectedItem())) {
            return true;
        }
        if (!isSlotVisible(item.getComponent())) {
            final Component scrollBar = ctx.widgets.get(ITEM_WIDGET, 47);
            if (scrollBar == null || !ctx.widgets.scroll(item.getComponent(), scrollBar, true)) {
                return false;
            }
        }
        if (item.getComponent().click(true)) {
            Condition.wait(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    return item.equals(getSelectedItem());
                }
            }, 10, 300);
        }
        return item.equals(getSelectedItem());
    }

    public Item getSelectedItem() {
        if (!isValid()) {
            return null;
        }
        final Component[] slots = ctx.widgets.get(ITEM_WIDGET, 44).getChildren();
        for (int i = 0; i < slots.length; i++) {
            final Component slot = slots[i];
            if (slot != null && slot.getItemId() != -1) {
                final int textureId = slots[i - 1].getTextureId();
                if (textureId == 15201 || textureId == 13989) {
                    return new Item(ctx, slot);
                }
            }
        }
        return null;
    }

    public Item getItem(final int... ids) {
        return getItem(new Filter<Item>() {
            public boolean accept(final Item item) {
                for (final int id : ids) {
                    if (id == item.getId()) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public Item[] getItems() {
        return getItems(new Filter<Item>() {
            public boolean accept(final Item item) {
                return true;
            }
        });
    }

    public Item[] getItems(final Filter<Item> itemFilter) {
        if (!isValid()) {
            return new Item[0];
        }
        final Component[] slots = ctx.widgets.get(ITEM_WIDGET, 44).getChildren();
        final ArrayList<Item> items = new ArrayList<>(slots.length);
        for (final Component slot : slots) {
            if (slot != null && slot.getItemId() != -1) {
                final Item item = new Item(ctx, slot);
                if (itemFilter.accept(item)) {
                    items.add(item);
                }
            }
        }
        return items.toArray(new Item[items.size()]);
    }

    public Item getItem(final Filter<Item> itemFilter) {
        for (final Item item : getItems()) {
            if (item != null && itemFilter.accept(item)) {
                return item;
            }
        }
        return null;
    }

    public boolean openCategoryList() {
        final Component w = ctx.widgets.get(ITEM_WIDGET, 62);
        if (w.isValid() && w.isVisible()) {
            return true;
        }
        final Component w2 = ctx.widgets.get(ITEM_WIDGET, 51);
        return w2.isValid() && w2.click(true);
    }

    public String getSelectedCategory() {
        final Component w = ctx.widgets.get(ITEM_WIDGET, 51).getChild(0);
        if (w.isValid()) {
            return w.getText();
        }
        return null;
    }

    public boolean selectCategory(final String category) {
        if (getSelectedCategory().equals(category)) {
            return true;
        }
        if (openCategoryList()) {
            final Component list = ctx.widgets.get(ITEM_WIDGET, 62);
            for (final Component wc : list.getChildren()) {
                if (wc.getText().equals(category)) {
                    return wc.click(true);
                }
            }
        }
        return false;
    }

    private boolean isSlotVisible(final Component slot) {
        final Component slots = ctx.widgets.get(ITEM_WIDGET, 44);
        final Rectangle visibleBounds = new Rectangle(
                slots.getAbsoluteLocation(),
                new Dimension(
                        slots.getWidth() - slot.getWidth(), slots.getHeight() - slot.getHeight()
                )
        );
        return visibleBounds.contains(slot.getAbsoluteLocation());
    }
}