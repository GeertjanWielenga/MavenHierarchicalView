package org.netbeans.mvnhieview;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ChangeListener;
import org.netbeans.api.project.Project;
import org.netbeans.spi.project.SubprojectProvider;
import org.netbeans.spi.project.ui.LogicalViewProvider;
import org.netbeans.spi.project.ui.support.NodeFactory;
import org.netbeans.spi.project.ui.support.NodeList;
import org.openide.nodes.FilterNode;
import org.openide.nodes.Node;

@NodeFactory.Registration(projectType = "org-netbeans-modules-maven", position = 400)
public class ModulesNodeFactory2 implements NodeFactory {

    @Override
    public NodeList<?> createNodes(Project prjct) {
        return new MavenModulesNodeList(prjct);
    }

    private class MavenModulesNodeList implements NodeList<Project> {

        private final Project project;

        public MavenModulesNodeList(Project prjct) {
            this.project = prjct;
        }

        @Override
        public List<Project> keys() {
            return new ArrayList<Project> (project.getLookup().
                lookup(SubprojectProvider.class).getSubprojects());
        }

        @Override
        public Node node(final Project project) {
            Node node = project.getLookup().
                lookup(LogicalViewProvider.class).createLogicalView();
            return new FilterNode(node, new FilterNode.Children(node));
        }

        @Override
        public void addChangeListener(ChangeListener cl) {
        }

        @Override
        public void removeChangeListener(ChangeListener cl) {
        }

        @Override
        public void addNotify() {
        }

        @Override
        public void removeNotify() {
        }
        
    }
    
}
