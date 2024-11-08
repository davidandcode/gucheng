package basic_algorithms.dfs;
import java.util.*;

class Package {
    private String name;
    private List<Package> dependencies;
    public Package(String name) {
        this.name = name;
        this.dependencies = new ArrayList<>();
    }
    public String getName() {
        return name;
    }
    public void addDependency(Package dependency) {
        dependencies.add(dependency);
    }
    public List<Package> getDependencies() {
        return dependencies;
    }
}

public class PackageManager {
    private List<Package> installedPackages;
    private Set<Package> visiting;
    public PackageManager() {
        installedPackages = new ArrayList<>();
        visiting = new HashSet<>();
    }
    public boolean installPackage(Package pkg) {
        List<Package> snapshot = new ArrayList<>(installedPackages);
        boolean success = install(pkg);
        if (!success) {
            rollbackInstalledPackages(snapshot);
        }
        return success;
    }
    private boolean install(Package pkg) {
        if (!isInstalled(pkg)) {
            if (visiting.contains(pkg)) {
                return false;// Circular dependency detected
            }
            visiting.add(pkg);
            for (Package dependency : pkg.getDependencies()) {
                if (!install(dependency)) {
                    visiting.remove(pkg);
                    return false;
                }
            }
            visiting.remove(pkg);
            installedPackages.add(pkg);
            System.out.println("Installed package: " + pkg.getName());
            return true;
        }
        System.out.println("Already installed package: " + pkg.getName());
        return true; // good install and already installed => true; circle => false
    }
    private void rollbackInstalledPackages(List<Package> snapshot) {
        installedPackages.clear();
        installedPackages.addAll(snapshot);
        System.out.println("Rollback installed packages");
    }
    public boolean isInstalled(Package pkg) {
        return installedPackages.contains(pkg);
    }
}




