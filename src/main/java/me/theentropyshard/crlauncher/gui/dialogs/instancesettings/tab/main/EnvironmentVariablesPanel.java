/*
 * CRLauncher - https://github.com/CRLauncher/CRLauncher
 * Copyright (C) 2024-2025 CRLauncher
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package me.theentropyshard.crlauncher.gui.dialogs.instancesettings.tab.main;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import me.theentropyshard.crlauncher.CRLauncher;
import me.theentropyshard.crlauncher.instance.CosmicInstance;
import me.theentropyshard.crlauncher.language.LanguageSection;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class EnvironmentVariablesPanel extends JPanel {
    private final MigLayout layout;
    private final JButton addEnvVarButton;
    private final String nameFieldPlaceholder;
    private final String valueFieldPlaceholder;

    private int row;

    public EnvironmentVariablesPanel(CosmicInstance instance) {
        this.layout = new MigLayout("fill, insets 2, gap 5 5", "[50%][50%][]", "[top]");

        this.setLayout(this.layout);

        LanguageSection section = CRLauncher.getInstance().getLanguage().getSection("gui.instanceSettingsDialog.mainTab.environmentVariables");

        this.setBorder(new TitledBorder(section.getString("borderName")));

        this.nameFieldPlaceholder = section.getString("nameFieldPlaceholder");
        this.valueFieldPlaceholder = section.getString("valueFieldPlaceholder");

        this.addEnvVarButton = new JButton(section.getString("addEnvVarButton"));
        this.addEnvVarButton.addActionListener(e -> {

        });

        instance.getEnvironmentVariables().forEach(this::addEnvironmentVariable);
    }

    public void addEnvironmentVariable(String name, String value) {
        this.remove(this.addEnvVarButton);

        /* TODO
            when variable is added, lock fields and add edit button with "edit" icon, when it is clicked,
            unlock fields and change the icon to "save"
         */

        JTextField nameField = new JTextField(name);
        nameField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, this.nameFieldPlaceholder);
        this.add(nameField, "growx, cell 0 " + this.row);

        JTextField valueField = new JTextField(value);
        valueField.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, this.valueFieldPlaceholder);
        this.add(valueField, "growx, cell 1 " + this.row);

        this.add(new JButton(new FlatSVGIcon(EnvironmentVariablesPanel.class.getResource("/assets/images/edit.svg"))),
            "wrap, cell 2 " + this.row);

        this.add(new JButton(new FlatSVGIcon(EnvironmentVariablesPanel.class.getResource("/assets/images/delete.svg"))),
            "wrap, cell 3 " + this.row);

        this.row++;

        this.add(this.addEnvVarButton, "span 4, growx");

        this.revalidate();
    }
}
