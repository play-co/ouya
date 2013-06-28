<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:android="http://schemas.android.com/apk/res/android">
	<xsl:template match="category[1]">
		<category android:name="android.intent.category.LAUNCHER" />
		<category android:name="tv.ouya.intent.category.GAME"/>
	</xsl:template>
</xsl:stylesheet>