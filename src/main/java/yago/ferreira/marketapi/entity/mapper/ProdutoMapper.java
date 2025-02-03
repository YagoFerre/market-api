package yago.ferreira.marketapi.entity.mapper;

import org.springframework.stereotype.Component;
import yago.ferreira.marketapi.entity.Avatar;
import yago.ferreira.marketapi.entity.File;
import yago.ferreira.marketapi.entity.Produto;
import yago.ferreira.marketapi.entity.Usuario;
import yago.ferreira.marketapi.entity.dto.AvatarDTO;
import yago.ferreira.marketapi.entity.dto.FileDTO;
import yago.ferreira.marketapi.entity.dto.ProdutoDTO;
import yago.ferreira.marketapi.entity.dto.UsuarioDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProdutoMapper {

    public ProdutoDTO toDTO(Produto produto) {
        if (produto == null) {
            return null;
        }

        ProdutoDTO produtoDTO = new ProdutoDTO();

        produtoDTO.setId(produto.getId());
        produtoDTO.setTitulo(produto.getTitulo());
        produtoDTO.setDescricao(produto.getDescricao());
        produtoDTO.setPreco(produto.getPreco());
        produtoDTO.setUsuario(toUsuarioDTO(produto.getUsuario()));
        produtoDTO.setProdutoImagem(toFileDTOList(produto.getProdutoImagem()));
        return produtoDTO;
    }

    public Produto toEntity(ProdutoDTO produtoDTO) {
        if (produtoDTO == null) {
            return null;
        }

        Produto produto = new Produto();

        produto.setId(produtoDTO.getId());
        produto.setTitulo(produtoDTO.getTitulo());
        produto.setDescricao(produtoDTO.getDescricao());
        produto.setPreco(produtoDTO.getPreco());
        produto.setUsuario(toUsuarioEntity(produtoDTO.getUsuario()));
        produto.setProdutoImagem(toFileEntityList(produtoDTO.getProdutoImagem()));
        return produto;
    }

    private UsuarioDTO toUsuarioDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }

        UsuarioDTO usuarioDTO = new UsuarioDTO();

        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setNome(usuario.getNome());
        usuarioDTO.setAvatar(toAvatarDTO(usuario.getAvatar()));
        return usuarioDTO;
    }

    private Usuario toUsuarioEntity(UsuarioDTO usuarioDTO) {
        if (usuarioDTO == null) {
            return null;
        }

        Usuario usuario = new Usuario();

        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setNome(usuarioDTO.getNome());
        usuario.setAvatar(toAvatarEntity(usuarioDTO.getAvatar()));
        return usuario;
    }

    private AvatarDTO toAvatarDTO(Avatar avatar) {
        if (avatar == null) {
            return null;
        }

        AvatarDTO avatarDTO = new AvatarDTO();

        avatarDTO.setId(avatar.getId());
        avatarDTO.setNome(avatar.getNome());
        avatarDTO.setFilePath(avatar.getFilePath());
        return avatarDTO;
    }

    private Avatar toAvatarEntity(AvatarDTO avatarDTO) {
        if (avatarDTO == null) {
            return null;
        }

        Avatar avatar = new Avatar();
        avatar.setId(avatarDTO.getId());
        avatar.setNome(avatarDTO.getNome());
        avatar.setFilePath(avatarDTO.getFilePath());
        return avatar;
    }

    private FileDTO toFileDTO(File file) {
        if (file == null) {
            return null;
        }

        FileDTO fileDTO = new FileDTO();

        fileDTO.setId(file.getId());
        fileDTO.setNome(file.getNome());
        fileDTO.setFilePath(file.getFilePath());
        return fileDTO;
    }

    private File toFileEntity(FileDTO fileDTO) {
        if (fileDTO == null) {
            return null;
        }

        File file = new File();

        file.setId(fileDTO.getId());
        file.setNome(fileDTO.getNome());
        file.setFilePath(fileDTO.getFilePath());
        return file;
    }

    private List<FileDTO> toFileDTOList(List<File> files) {
        if (files == null) {
            return Collections.emptyList();
        }

        List<FileDTO> filesDTO = new ArrayList<>();

        for (File file : files) {
            filesDTO.add(toFileDTO(file));
        }

        return filesDTO;
    }

    private List<File> toFileEntityList(List<FileDTO> filesDTO) {
        if (filesDTO == null) {
            return Collections.emptyList();
        }

        List<File> files = new ArrayList<>();

        for (FileDTO fileDTO : filesDTO) {
            files.add(toFileEntity(fileDTO));
        }

        return files;
    }

}
