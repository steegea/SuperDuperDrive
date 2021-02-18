    // For opening the note modal
        function showNoteModal(noteId, noteTitle, noteDescription) {
        $('#note-id').val(noteId ? noteId : 0);
        $('#noteTitleInput').val(noteTitle ? noteTitle : '');
        $('#noteDescriptionInput').val(noteDescription ? noteDescription : '');

        $('#noteModal').modal('show');
    }

    // For opening the credentials modal
    function showCredentialModal(credentialID, url, username, password) {
        $('#credentialID').val(credentialID ? credentialID : 0);
        $('#credentialURL').val(url ? url : '');
        $('#credentialUsername').val(username ? username : '');
        $('#credentialPassword').val(password ? password : '');
        $('#credentialModal').modal('show');
    }

    function showDeleteNoteModal(noteID){
        $("#deleteNoteID").val(noteID);
        $('#deleteNoteModal').modal("show");
    }

    function showDeleteCredentialModal(){
        $("#deleteCredentialsModal").modal("show");
    }

    function showDeleteFileModal(fileID){
        $("#deleteFileID").val(fileID);
        $("#deleteFileModal").modal("show");
    }


    //Disables the file upload button if no file is selected
    $(document).ready(
        function(){
            $("#uploadNewFileButton").attr('disabled',true);

            $("#fileToUpload").change(
                function(){
                    if ($("#fileToUpload").val()){
                        $("#uploadNewFileButton").removeAttr('disabled');
                    }

                    else {
                        $("#uploadNewFileButton").attr('disabled',true);
                    }
                });
        }
    );