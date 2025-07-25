package com.coolerpromc.custombiomes.util;

import com.google.common.base.Preconditions;
import net.minecraft.util.Mth;
import net.minecraft.world.level.levelgen.synth.ImprovedNoise;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public final class WeightedPicker<T> {
    private double currentTotal;
    private final List<WeightedEntry<T>> entries;

    public WeightedPicker() {
        this((double)0.0F, new ArrayList<>());
    }

    private WeightedPicker(double currentTotal, List<WeightedEntry<T>> entries) {
        this.currentTotal = currentTotal;
        this.entries = entries;
    }

    public void add(T biome, double weight) {
        this.currentTotal += weight;
        this.entries.add(new WeightedEntry<>(biome, weight, this.currentTotal));
    }

    double getCurrentWeightTotal() {
        return this.currentTotal;
    }

    public int getEntryCount() {
        return this.entries.size();
    }

    public T pickFromNoise(ImprovedNoise sampler, double x, double y, double z) {
        double target = Mth.clamp(Math.abs(sampler.noise(x, y, z)), (double)0.0F, (double)1.0F) * this.getCurrentWeightTotal();
        return (T)this.search(target).entry();
    }

    public <U> WeightedPicker<U> map(Function<T, U> mapper) {
        return new WeightedPicker<U>(this.currentTotal, this.entries.stream().map((e) -> new WeightedEntry<>(mapper.apply(e.entry), e.weight, e.upperWeightBound)).toList());
    }

    WeightedEntry<T> search(double target) {
        Preconditions.checkArgument(target <= this.currentTotal, "The provided target value for entry selection must be less than or equal to the weight total");
        Preconditions.checkArgument(target >= (double)0.0F, "The provided target value for entry selection cannot be negative");
        int low = 0;
        int high = this.entries.size() - 1;

        while(low < high) {
            int mid = high + low >>> 1;
            if (target < this.entries.get(mid).upperWeightBound()) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return this.entries.get(low);
    }

    public record WeightedEntry<T>(T entry, double weight, double upperWeightBound) {

    }
}
