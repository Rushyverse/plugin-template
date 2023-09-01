package com.github.rushyverse.template.craft

import com.github.rushyverse.api.Plugin
import com.github.rushyverse.api.extension.material
import com.github.rushyverse.api.extension.registerCraft
import com.github.rushyverse.api.item.CraftSlot
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemStack

private const val ENCHANT_DAMAGE_ALL_LEVEL = 10

/**
 * Register a new craft for the ultimate sword.
 * @receiver The plugin where the craft will be registered.
 */
fun Plugin.registerUltimateSwordCraft() {
    registerCraft {
        val stick = ItemStack(Material.STICK)
        set(CraftSlot.CenterLeft, stick)
        set(CraftSlot.CenterRight, stick)
        set(CraftSlot.Center, ItemStack(Material.STONE))
        result {
            material = Material.NETHERITE_SWORD
            addUnsafeEnchantment(Enchantment.DAMAGE_ALL, ENCHANT_DAMAGE_ALL_LEVEL)
        }
    }
}
