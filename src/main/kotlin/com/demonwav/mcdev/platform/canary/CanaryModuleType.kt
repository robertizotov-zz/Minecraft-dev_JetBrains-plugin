/*
 * Minecraft Dev for IntelliJ
 *
 * https://minecraftdev.org
 *
 * Copyright (c) 2017 minecraft-dev
 *
 * MIT License
 */

package com.demonwav.mcdev.platform.canary

import com.demonwav.mcdev.asset.PlatformAssets
import com.demonwav.mcdev.facet.MinecraftFacet
import com.demonwav.mcdev.platform.AbstractModuleType
import com.demonwav.mcdev.platform.PlatformType
import com.demonwav.mcdev.platform.canary.generation.CanaryHookGenerationPanel
import com.demonwav.mcdev.platform.canary.util.CanaryConstants
import com.demonwav.mcdev.platform.canary.util.CanaryLegacyColors
import com.demonwav.mcdev.util.CommonColors
import com.intellij.psi.PsiClass

object CanaryModuleType : AbstractModuleType<CanaryModule<CanaryModuleType>>("net.canarymod", "CanaryLib") {

    private const val ID = "CANARY_MODULE_TYPE"

    val IGNORED_ANNOTATIONS = listOf(
        CanaryConstants.HOOK_HANDLER_ANNOTATION,
        CanaryConstants.COMMAND_ANNOTATION,
        CanaryConstants.TAB_COMPLETE_ANNOTATION,
        CanaryConstants.COLUMN_ANNOTATION
    )
    val LISTENER_ANNOTATIONS = listOf(CanaryConstants.HOOK_HANDLER_ANNOTATION)

    init {
        CommonColors.applyStandardColors(colorMap, CanaryConstants.CHAT_FORMAT_CLASS)
        CanaryLegacyColors.applyLegacyColors(colorMap, CanaryConstants.LEGACY_COLORS_CLASS)
        CanaryLegacyColors.applyLegacyColors(colorMap, CanaryConstants.LEGACY_TEXT_FORMAT_CLASS)
    }

    override fun getPlatformType() = PlatformType.CANARY
    override fun getIcon() = PlatformAssets.CANARY_ICON
    override fun getId() = ID
    override fun getIgnoredAnnotations() = IGNORED_ANNOTATIONS
    override fun getListenerAnnotations() = LISTENER_ANNOTATIONS
    override fun generateModule(facet: MinecraftFacet) = CanaryModule(facet, this)
    override fun isEventGenAvailable() = true
    override fun getEventGenerationPanel(chosenClass: PsiClass) = CanaryHookGenerationPanel(chosenClass)
}
