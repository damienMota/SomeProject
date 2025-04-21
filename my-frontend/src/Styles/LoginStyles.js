import { styled } from '@mui/material';

const LoginContainer = styled('div')({
  maxWidth: 400,
  margin: 'auto',
  marginTop: (theme) => theme.spacing(10),
  padding: (theme) => theme.spacing(2),
});

const FormGroup = styled('div')({
  margin: (theme) => theme.spacing(2),
});

const InputField = styled('input')({
  width: '100%',
  padding: (theme) => theme.spacing(1),
  margin: (theme) => theme.spacing(1),
});

const SubmitButton = styled('button')({
  width: '100%',
  padding: (theme) => theme.spacing(1),
  margin: (theme) => theme.spacing(1),
});

const ErrorMessage = styled('p')({
  color: 'red',
  margin: (theme) => theme.spacing(1),
});

export { LoginContainer, FormGroup, InputField, SubmitButton, ErrorMessage };