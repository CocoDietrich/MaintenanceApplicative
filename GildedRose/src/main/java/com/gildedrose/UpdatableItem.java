package com.gildedrose;

abstract class UpdatableItem {
    protected Item item;

    public UpdatableItem(Item item) {
        this.item = item;
    }

    public abstract void update();

    protected void decreaseSellIn() {
        item.sellIn--;
    }

    protected void increaseQuality(int amount) {
        item.quality = Math.min(50, item.quality + amount);
    }

    protected void decreaseQuality(int amount) {
        item.quality = Math.max(0, item.quality - amount);
    }

    protected Item getItem() {
        return item;
    }
}