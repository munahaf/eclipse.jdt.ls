/*******************************************************************************
 * Copyright (c) 2016-2017 Red Hat Inc. and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Red Hat Inc. - initial API and implementation
 *******************************************************************************/
package org.eclipse.jdt.ls.core.internal.preferences;

import static org.eclipse.jdt.ls.core.internal.handlers.MapFlattener.getBoolean;
import static org.eclipse.jdt.ls.core.internal.handlers.MapFlattener.getList;
import static org.eclipse.jdt.ls.core.internal.handlers.MapFlattener.getString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.eclipse.lsp4j.MessageType;

/**
 * Preferences model
 *
 * @author Fred Bricon
 *
 */
public class Preferences {

	/**
	 * Specifies the folder path to the JDK .
	 */
	public static final String JAVA_HOME = "java.home";
	/**
	 * Preference key to enable/disable gradle importer.
	 */
	public static final String IMPORT_GRADLE_ENABLED = "java.import.gradle.enabled";
	/**
	 * Preference key to enable/disable maven importer.
	 */
	public static final String IMPORT_MAVEN_ENABLED = "java.import.maven.enabled";
	/**
	 * Preference key to enable/disable reference code lenses.
	 */
	public static final String REFERENCES_CODE_LENS_ENABLED_KEY = "java.referencesCodeLens.enabled";

	/**
	 * Preference key to enable/disable implementation code lenses.
	 */
	public static final String IMPLEMENTATIONS_CODE_LENS_ENABLED_KEY = "java.implementationsCodeLens.enabled";

	/**
	 * Preference key to enable/disable formatter.
	 */
	public static final String JAVA_FORMAT_ENABLED_KEY = "java.format.enabled";

	/**
	 * Preference key to enable/disable signature help.
	 */
	public static final String SIGNATURE_HELP_ENABLED_KEY = "java.signatureHelp.enabled";

	/**
	 * Preference key to enable/disable rename.
	 */
	public static final String RENAME_ENABLED_KEY = "java.rename.enabled";

	/**
	 * Preference key to enable/disable executeCommand.
	 */
	public static final String EXECUTE_COMMAND_ENABLED_KEY = "java.executeCommand.enabled";

	/**
	 * Preference key to exclude directories when importing projects.
	 */
	public static final String JAVA_IMPORT_EXCLUSIONS_KEY = "java.import.exclusions";
	public static final List<String> JAVA_IMPORT_EXCLUSIONS_DEFAULT;

	/**
	 * Preference key for project build/configuration update settings.
	 */
	public static final String CONFIGURATION_UPDATE_BUILD_CONFIGURATION_KEY = "java.configuration.updateBuildConfiguration";

	/**
	 * Preference key for incomplete classpath severity messages.
	 */
	public static final String ERRORS_INCOMPLETE_CLASSPATH_SEVERITY_KEY = "java.errors.incompleteClasspath.severity";

	/**
	 * Preference key for Maven user settings.xml location.
	 */
	public static final String MAVEN_USER_SETTINGS_KEY = "java.configuration.maven.userSettings";

	/**
	 * A named preference that holds the favorite static members.
	 * <p>
	 * Value is of type <code>String</code>: semicolon separated list of
	 * favorites.
	 * </p>
	 */
	public static final String FAVORITE_STATIC_MEMBERS = "java.favoriteStaticMembers";

	/**
	 * A named preference that defines how member elements are ordered by code
	 * actions.
	 * <p>
	 * Value is of type <code>String</code>: A comma separated list of the
	 * following entries. Each entry must be in the list, no duplication. List
	 * order defines the sort order.
	 * <ul>
	 * <li><b>T</b>: Types</li>
	 * <li><b>C</b>: Constructors</li>
	 * <li><b>I</b>: Initializers</li>
	 * <li><b>M</b>: Methods</li>
	 * <li><b>F</b>: Fields</li>
	 * <li><b>SI</b>: Static Initializers</li>
	 * <li><b>SM</b>: Static Methods</li>
	 * <li><b>SF</b>: Static Fields</li>
	 * </ul>
	 * </p>
	 */
	public static final String MEMBER_SORT_ORDER = "java.memberSortOrder"; //$NON-NLS-1$

	/**
	 * Preference key for the id(s) of the preferred content provider(s).
	 */
	public static final String PREFERRED_CONTENT_PROVIDER_KEY = "java.contentProvider.preferred";

	public static final String TEXT_DOCUMENT_FORMATTING = "textDocument/formatting";
	public static final String TEXT_DOCUMENT_RANGE_FORMATTING = "textDocument/rangeFormatting";
	public static final String TEXT_DOCUMENT_CODE_LENS = "textDocument/codeLens";
	public static final String TEXT_DOCUMENT_SIGNATURE_HELP = "textDocument/signatureHelp";
	public static final String TEXT_DOCUMENT_RENAME = "textDocument/rename";
	public static final String WORKSPACE_EXECUTE_COMMAND = "workspace/executeCommand";
	public static final String WORKSPACE_SYMBOL = "workspace/symbol";
	public static final String DOCUMENT_SYMBOL = "textDocument/documentSymbol";
	public static final String CODE_ACTION = "textDocument/codeAction";
	public static final String DEFINITION = "textDocument/definition";
	public static final String HOVER = "textDocument/hover";
	public static final String REFERENCES = "textDocument/references";
	public static final String DOCUMENT_HIGHLIGHT = "textDocument/documentHighlight";

	public static final String FORMATTING_ID = UUID.randomUUID().toString();
	public static final String FORMATTING_RANGE_ID = UUID.randomUUID().toString();
	public static final String CODE_LENS_ID = UUID.randomUUID().toString();
	public static final String SIGNATURE_HELP_ID = UUID.randomUUID().toString();
	public static final String RENAME_ID = UUID.randomUUID().toString();
	public static final String EXECUTE_COMMAND_ID = UUID.randomUUID().toString();
	public static final String WORKSPACE_SYMBOL_ID = UUID.randomUUID().toString();
	public static final String DOCUMENT_SYMBOL_ID = UUID.randomUUID().toString();
	public static final String CODE_ACTION_ID = UUID.randomUUID().toString();
	public static final String DEFINITION_ID = UUID.randomUUID().toString();
	public static final String HOVER_ID = UUID.randomUUID().toString();
	public static final String REFERENCES_ID = UUID.randomUUID().toString();
	public static final String DOCUMENT_HIGHLIGHT_ID = UUID.randomUUID().toString();

	private Map<String, Object> configuration;
	private Severity incompleteClasspathSeverity;
	private FeatureStatus updateBuildConfigurationStatus;
	private boolean referencesCodeLensEnabled;
	private boolean importGradleEnabled;
	private boolean importMavenEnabled;
	private boolean implementationsCodeLensEnabled;
	private boolean javaFormatEnabled;
	private boolean signatureHelpEnabled;
	private boolean renameEnabled;
	private boolean executeCommandEnabled;
	private MemberSortOrder memberOrders;
	private List<String> preferredContentProviderIds;

	private String mavenUserSettings;

	private String favoriteStaticMembers;

	private List<String> javaImportExclusions = new ArrayList<>();
	private String javaHome;

	static {
		JAVA_IMPORT_EXCLUSIONS_DEFAULT = new ArrayList<>();
		JAVA_IMPORT_EXCLUSIONS_DEFAULT.add("**/node_modules");
		JAVA_IMPORT_EXCLUSIONS_DEFAULT.add("**/.metadata");
		JAVA_IMPORT_EXCLUSIONS_DEFAULT.add("**/archetype-resources");
	}
	public static enum Severity {
		ignore, log, info, warning, error;

		static Severity fromString(String value, Severity defaultSeverity) {
			if (value != null) {
				String val = value.toLowerCase();
				try {
					return valueOf(val);
				} catch(Exception e) {
					//fall back to default severity
				}
			}
			return defaultSeverity;
		}

		public MessageType toMessageType() {
			for (MessageType type : MessageType.values()) {
				if (name().equalsIgnoreCase(type.name())) {
					return type;
				}
			}
			//'ignore' has no MessageType equivalent
			return null;
		}
	}

	public static enum FeatureStatus {
		disabled, interactive, automatic ;

		static FeatureStatus fromString(String value, FeatureStatus defaultStatus) {
			if (value != null) {
				String val = value.toLowerCase();
				try {
					return valueOf(val);
				} catch(Exception e) {
					//fall back to default severity
				}
			}
			return defaultStatus;
		}
	}

	public Preferences() {
		configuration = null;
		incompleteClasspathSeverity = Severity.warning;
		updateBuildConfigurationStatus = FeatureStatus.interactive;
		importGradleEnabled = true;
		importMavenEnabled = true;
		referencesCodeLensEnabled = true;
		implementationsCodeLensEnabled = false;
		javaFormatEnabled = true;
		signatureHelpEnabled = false;
		renameEnabled = true;
		executeCommandEnabled = true;
		memberOrders = new MemberSortOrder(null);
		preferredContentProviderIds = null;
		favoriteStaticMembers = "";
		javaImportExclusions = JAVA_IMPORT_EXCLUSIONS_DEFAULT;
		javaHome = null;
	}

	/**
	 * Create a {@link Preferences} model from a {@link Map} configuration.
	 */
	public static Preferences createFrom(Map<String, Object> configuration) {
		if (configuration == null) {
			throw new IllegalArgumentException("Configuration can not be null");
		}
		Preferences prefs = new Preferences();
		prefs.configuration = configuration;

		String incompleteClasspathSeverity = getString(configuration, ERRORS_INCOMPLETE_CLASSPATH_SEVERITY_KEY, null);
		prefs.setIncompleteClasspathSeverity(Severity.fromString(incompleteClasspathSeverity, Severity.warning));

		String updateBuildConfiguration = getString(configuration, CONFIGURATION_UPDATE_BUILD_CONFIGURATION_KEY, null);
		prefs.setUpdateBuildConfigurationStatus(
				FeatureStatus.fromString(updateBuildConfiguration, FeatureStatus.interactive));

		boolean importGradleEnabled = getBoolean(configuration, IMPORT_GRADLE_ENABLED, true);
		prefs.setImportGradleEnabled(importGradleEnabled);
		boolean importMavenEnabled = getBoolean(configuration, IMPORT_MAVEN_ENABLED, true);
		prefs.setImportMavenEnabled(importMavenEnabled);
		boolean referenceCodelensEnabled = getBoolean(configuration, REFERENCES_CODE_LENS_ENABLED_KEY, true);
		prefs.setReferencesCodelensEnabled(referenceCodelensEnabled);
		boolean implementationCodeLensEnabled = getBoolean(configuration, IMPLEMENTATIONS_CODE_LENS_ENABLED_KEY, false);
		prefs.setImplementationCodelensEnabled(implementationCodeLensEnabled);

		boolean javaFormatEnabled = getBoolean(configuration, JAVA_FORMAT_ENABLED_KEY, true);
		prefs.setJavaFormatEnabled(javaFormatEnabled);

		boolean signatureHelpEnabled = getBoolean(configuration, SIGNATURE_HELP_ENABLED_KEY, true);
		prefs.setSignatureHelpEnabled(signatureHelpEnabled);

		boolean renameEnabled = getBoolean(configuration, RENAME_ENABLED_KEY, true);
		prefs.setRenameEnabled(renameEnabled);

		boolean executeCommandEnable = getBoolean(configuration, EXECUTE_COMMAND_ENABLED_KEY, true);
		prefs.setExecuteCommandEnabled(executeCommandEnable);

		List<String> javaImportExclusions = getList(configuration, JAVA_IMPORT_EXCLUSIONS_KEY, JAVA_IMPORT_EXCLUSIONS_DEFAULT);
		prefs.setJavaImportExclusions(javaImportExclusions);

		String mavenUserSettings = getString(configuration, MAVEN_USER_SETTINGS_KEY, null);
		prefs.setMavenUserSettings(mavenUserSettings);

		String sortOrder = getString(configuration, MEMBER_SORT_ORDER, null);
		prefs.setMembersSortOrder(sortOrder);

		String favoriteStaticMembers = getString(configuration, FAVORITE_STATIC_MEMBERS, "");
		prefs.setFavoriteStaticMembers(favoriteStaticMembers);

		List<String> preferredContentProviders = getList(configuration, PREFERRED_CONTENT_PROVIDER_KEY);
		prefs.setPreferredContentProviderIds(preferredContentProviders);

		String javaHome = getString(configuration, JAVA_HOME);
		prefs.setJavaHome(javaHome);

		return prefs;
	}

	public Preferences setJavaHome(String javaHome) {
		this.javaHome = javaHome;
		return this;
	}

	public Preferences setJavaImportExclusions(List<String> javaImportExclusions) {
		this.javaImportExclusions = javaImportExclusions;
		return this;
	}

	private Preferences setMembersSortOrder(String sortOrder) {
		this.memberOrders = new MemberSortOrder(sortOrder);
		return this;
	}

	private Preferences setPreferredContentProviderIds(List<String> preferredContentProviderIds) {
		this.preferredContentProviderIds = preferredContentProviderIds;
		return this;
	}

	private Preferences setReferencesCodelensEnabled(boolean enabled) {
		this.referencesCodeLensEnabled = enabled;
		return this;
	}

	public Preferences setImportGradleEnabled(boolean enabled) {
		this.importGradleEnabled = enabled;
		return this;
	}

	public Preferences setImportMavenEnabled(boolean enabled) {
		this.importMavenEnabled = enabled;
		return this;
	}

	private Preferences setSignatureHelpEnabled(boolean enabled) {
		this.signatureHelpEnabled = enabled;
		return this;
	}

	private Preferences setImplementationCodelensEnabled(boolean enabled) {
		this.implementationsCodeLensEnabled = enabled;
		return this;
	}

	private Preferences setRenameEnabled(boolean enabled) {
		this.renameEnabled = enabled;
		return this;
	}

	private Preferences setExecuteCommandEnabled(boolean enabled) {
		this.executeCommandEnabled = enabled;
		return this;
	}

	public Preferences setJavaFormatEnabled(boolean enabled) {
		this.javaFormatEnabled = enabled;
		return this;
	}

	public Preferences setUpdateBuildConfigurationStatus(FeatureStatus status) {
		this.updateBuildConfigurationStatus = status;
		return this;
	}

	private Preferences setIncompleteClasspathSeverity(Severity severity) {
		this.incompleteClasspathSeverity = severity;
		return this;
	}

	public Preferences setFavoriteStaticMembers(String favoriteStaticMembers) {
		this.favoriteStaticMembers = favoriteStaticMembers;
		return this;
	}

	public Severity getIncompleteClasspathSeverity() {
		return incompleteClasspathSeverity;
	}

	public FeatureStatus getUpdateBuildConfigurationStatus() {
		return updateBuildConfigurationStatus;
	}

	public List<String> getJavaImportExclusions() {
		return javaImportExclusions;
	}

	public String getJavaHome() {
		return javaHome;
	}

	public MemberSortOrder getMemberSortOrder() {
		return this.memberOrders;
	}

	public List<String> getPreferredContentProviderIds() {
		return this.preferredContentProviderIds;
	}

	public boolean isReferencesCodeLensEnabled() {
		return referencesCodeLensEnabled;
	}

	public boolean isImportGradleEnabled() {
		return importGradleEnabled;
	}

	public boolean isImportMavenEnabled() {
		return importMavenEnabled;
	}

	public boolean isImplementationsCodeLensEnabled() {
		return implementationsCodeLensEnabled;
	}

	public boolean isCodeLensEnabled() {
		return referencesCodeLensEnabled || implementationsCodeLensEnabled;
	}

	public boolean isJavaFormatEnabled() {
		return javaFormatEnabled;
	}

	public boolean isSignatureHelpEnabled() {
		return signatureHelpEnabled;
	}

	public boolean isRenameEnabled() {
		return renameEnabled;
	}

	public boolean isExecuteCommandEnabled() {
		return executeCommandEnabled;
	}

	public Preferences setMavenUserSettings(String mavenUserSettings) {
		this.mavenUserSettings = mavenUserSettings;
		return this;
	}

	public String getMavenUserSettings() {
		return mavenUserSettings;
	}

	public String getFavoriteStaticMembers() {
		return this.favoriteStaticMembers;
	}

	public Map<String, Object> asMap() {
		if (configuration == null) {
			return null;
		}
		return Collections.unmodifiableMap(configuration);
	}
}
