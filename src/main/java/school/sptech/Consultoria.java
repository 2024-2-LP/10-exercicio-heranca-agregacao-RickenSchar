package school.sptech;

import school.sptech.especialistas.DesenvolvedorMobile;
import school.sptech.especialistas.DesenvolvedorWeb;

import java.util.ArrayList;
import java.util.List;

public class Consultoria {

    private String nome;
    private Integer vagas;
    private List<Desenvolvedor> desenvolvedores;

    public Consultoria() {
    }

    public Consultoria(String nome, Integer vagas) {
        this.nome = nome;
        this.vagas = vagas;
        this.desenvolvedores = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getVagas() {
        return vagas;
    }

    public void setVagas(Integer vagas) {
        this.vagas = vagas;
    }

    public Boolean contratar(Desenvolvedor desenvolvedor){

        if (desenvolvedor == null){
            return false;
        }

        if (desenvolvedores.size() < vagas){
            desenvolvedores.add(desenvolvedor);
            return true;
        }

        return false;

    }

    public Boolean contratarFullstack(DesenvolvedorWeb desenvolvedor){

        if (desenvolvedor.isFullstack()){

            desenvolvedores.add(desenvolvedor);
            return true;

        }

        else {
            return false;
        }

    }

    public Double getTotalSalarios(){

        Double salarios = 0.0;

        for (int i = 0; i < desenvolvedores.size(); i++) {

            salarios = desenvolvedores.get(i).calcularSalario() + salarios;

        }

        return salarios;

    }

    public Integer qtdDesenvolvedoresMobile(){

        Integer devsMob = 0;

        for (int i = 0; i < desenvolvedores.size(); i++) {

            if (desenvolvedores.get(i) instanceof DesenvolvedorMobile){
                devsMob++;
            }

        }

        return devsMob;

    }

    public List<Desenvolvedor> buscarPorSalarioMaiorIgualQue(Double salario){

        if (salario == null){
            throw new RuntimeException("42");
        }

        List<Desenvolvedor> dinheiro = new ArrayList<>();

        for (int i = 0; i < desenvolvedores.size(); i++) {

            if (desenvolvedores.get(i).calcularSalario() >= salario){
                dinheiro.add(desenvolvedores.get(i));
            }

        }

        return dinheiro;

    }

    public Desenvolvedor buscarMenorSalario(){

        if (desenvolvedores.isEmpty()){
            return null;
        }

        Desenvolvedor menosDinheiro = desenvolvedores.get(0);

        for (int i = 0; i < desenvolvedores.size(); i++) {

            if (menosDinheiro.calcularSalario() > desenvolvedores.get(i).calcularSalario()){

                menosDinheiro = desenvolvedores.get(i);

            }

        }

        return menosDinheiro;

    }

    public List<Desenvolvedor> buscarPorTecnologia(String tecnologia){

        if (tecnologia == null){
            throw new RuntimeException("42");
        }

        List<Desenvolvedor> desenvolvedoresTec = new ArrayList<>();

        for (int i = 0; i < desenvolvedores.size(); i++) {

            if (desenvolvedores.get(i) instanceof DesenvolvedorWeb){
                if ( ((DesenvolvedorWeb) desenvolvedores.get(i)).getBackend().equals(tecnologia) ||
                     ((DesenvolvedorWeb) desenvolvedores.get(i)).getFrontend().equals(tecnologia) ||
                     ((DesenvolvedorWeb) desenvolvedores.get(i)).getSgbd().equals(tecnologia)
                ){
                    desenvolvedoresTec.add(desenvolvedores.get(i));
                }
            }

            if (desenvolvedores.get(i) instanceof DesenvolvedorMobile){
                if ( ((DesenvolvedorMobile) desenvolvedores.get(i)).getLinguagem().equals(tecnologia) ||
                     ((DesenvolvedorMobile) desenvolvedores.get(i)).getPlataforma().equals(tecnologia)
                ){
                    desenvolvedoresTec.add(desenvolvedores.get(i));
                }
            }

        }

        return desenvolvedoresTec;

    }

    public Double getTotalSalariosPorTecnologia(String tecnologia){

        Double soma = 0.0;

        List<Desenvolvedor> devs = buscarPorTecnologia(tecnologia);

        for (int i = 0; i < devs.size(); i++) {

            soma = devs.get(i).calcularSalario() + soma;

        }

        return soma;

    }

}
