<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:android="http://schemas.android.com/apk/res/android">
	<xsl:template match="activity/intent-filter[1]">
		<xsl:copy>
			<xsl:call-template name="copy-children"/>
			<xsl:element name="category">
				<xsl:attribute name="android:name">ouya.intent.category.GAME</xsl:attribute>
			</xsl:element>
		</xsl:copy>
	</xsl:template>
	<xsl:template name="copy-children">
		<xsl:copy-of select="./*"/>
	</xsl:template>
	<xsl:template match="@*|node()">
		<xsl:copy>
			<xsl:apply-templates select="@*|node()" />
		</xsl:copy>
	</xsl:template>
</xsl:stylesheet>